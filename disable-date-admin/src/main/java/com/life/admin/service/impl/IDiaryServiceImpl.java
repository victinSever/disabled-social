package com.life.admin.service.impl;

import com.life.admin.dao.DiaryMapper;
import com.life.admin.pojo.Diary;
import com.life.admin.service.IDiaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class IDiaryServiceImpl extends ServiceImpl<DiaryMapper,Diary> implements IDiaryService {

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
}
