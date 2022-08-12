package com.life.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.life.admin.dao.DiaryMapper;
import com.life.admin.pojo.Diary;
import com.life.admin.pojo.DiaryDoc;
import com.life.admin.service.IDiaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class IDiaryServiceImpl extends ServiceImpl<DiaryMapper,Diary> implements IDiaryService {

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private DiaryMapper diaryMapper;


    //获取（没有审核通过的）动态列表
    public List<Diary> getVideoDiaryList(Integer index, Integer size) {
        return diaryMapper.getVideoDiaryList(index,size);
    }

    //获取（待审核的非视频类的）动态列表
    public List<Diary> getNotVideoDiaryList(Integer index, Integer size) {
        return diaryMapper.getNotVideoDiaryList(index,size);
    }

    //获取未审核通过的视频类动态数目
    public Integer getVideoDiarys() {
        List<Diary> diaries = new ArrayList<Diary>();
        diaries = diaryMapper.getVideoDiarys();
        Integer all = diaries.size();
        return all;
    }

    //获取待审核的图文类动态数目
    public Integer getNotVideoDiarys() {
        List<Diary> diaries = new ArrayList<Diary>();
        diaries = diaryMapper.getNotVideoDiarys();
        Integer all = diaries.size();
        return all;
    }

    //更新动态审核结果
    public void updateAuditDiaryResult(String diaryId, Integer isDeleted, String ps) {
        diaryMapper.updateAuditDiaryResult( diaryId,  isDeleted,  ps);
    }

    //更新举报状态
    public void reportResult(Integer id, Integer status,String ps) {
        diaryMapper.reportResult( id,  status, ps);
    }

    @Override
    public void insertDiaryToIndexById(String diaryId) {
        try {
            // 根据id查询酒店数据
            Diary diary = getDiaryByDiaryId(diaryId);

            //转换为文档类型
            DiaryDoc diaryDoc = new DiaryDoc(diary);

            //1.准备Request对象
            IndexRequest request = new IndexRequest("tb_disable_date_diary").id(diaryDoc.getId().toString());
            //2.准备Json文档
            request.source(JSON.toJSONString(diaryDoc), XContentType.JSON);
            //3.发送请求
            client.index(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Diary getDiaryByDiaryId(String diaryId) {
        try {
//            String index  = "tb_disable_date_diary";  //索引库名（==表名）
//            String esName = "diaryId";       //查询的字段名
//            List<Diary> diaries = new ArrayList<Diary>();
//            SearchHit[] hits = restClient.boolQuery_termQuery_getAllByOneText(index,esName,diaryId);
//            //反序列
//            for(SearchHit hit : hits){
//                //获取文档source
//                String json = hit.getSourceAsString();
//                //反序列化
//                Diary diary = JSON.parseObject(json,Diary.class);
//                diaries.add(diary);
//            }
//
//            if(diaries.size()!=0)
//                return diaries.get(0);
//            else return null;
            return diaryMapper.getDiaryById(diaryId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
