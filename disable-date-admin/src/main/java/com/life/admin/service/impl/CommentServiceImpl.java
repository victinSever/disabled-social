package com.life.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.life.admin.common.RestClient;
import com.life.admin.dao.CommentMapper;
import com.life.admin.pojo.Comment;
import com.life.admin.pojo.Reply;
import com.life.admin.service.ICommentService;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private RestClient restClient;

    //查询全部
    public List<Comment> getAll() {
        return commentMapper.getAll();
    }

    //根据commenId找出评论并新增到索引库
    public void insertCommentToIndexById(String commentId) {
        try {
            // 根据id查询酒店数据
            Comment comment = getCommentById(commentId);

            //1.准备Request对象
            IndexRequest request = new IndexRequest("tb_disable_date_diary_comment").id(comment.getId().toString());
            //2.准备Json文档
            request.source(JSON.toJSONString(comment), XContentType.JSON);
            //3.发送请求
            client.index(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //根据replyId找出回复并新增到索引库
    public void insertReplyToIndexById(String replyId) {
        try {
            // 根据id查询酒店数据
            Reply reply = getReplyById(replyId);

            //1.准备Request对象
            IndexRequest request = new IndexRequest("tb_disable_date_diary_reply").id(reply.getId().toString());
            //2.准备Json文档
            request.source(JSON.toJSONString(reply), XContentType.JSON);
            //3.发送请求
            client.index(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //根据评论的id 查找其所有的 回复（在索引库中查找）
    public List<Reply> getAllReplyByDiaryId(String page,String size,String sortName,String sortOrder,String commentId) {
        try {
            String index  = "tb_disable_date_diary_reply";  //索引库名（==表名）
            String esName = "commentId";       //查询的字段名
            String esStatus = "replyStatus";
            Map<String,String> es = new HashMap<String, String>();
            es.put("replyStatus","1");
            SearchHit[] hits = restClient.getAllByISSPG(index,sortName,page,size,sortOrder,esName,commentId,es);
            List<Reply> list = new ArrayList<Reply>();
            int n = 0;
            for(SearchHit hit : hits){
                n++;
                //获取文档source
                String json = hit.getSourceAsString();
                //反序列化
                Reply reply = JSON.parseObject(json,Reply.class);
                list.add(reply);
                System.out.println("------------------------------------------------------------------------");
                System.out.println("获取到第 "+n+" 个结果 --> "+reply);
                System.out.println("------------------------------------------------------------------------");
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //从数据库中删除动态的所有评论
    public void deleteCommments(String diaryId) {
        commentMapper.deleteComments(diaryId);
    }

    //从数据库中删除动态的所有评论的所有回复
    public void deleteReplies(String diaryId) {
        commentMapper.deleteReplies(diaryId);
    }

    //根据replyId找出回复并新增到索引库
    public Reply getReplyById(String replyId) {
        return commentMapper.getReplyById(replyId);
    }

    //根据commenId找出评论
    public Comment getCommentById(String commentId) {
        return commentMapper.getCommentById(commentId);
    }

    public void deleteCommentsFromIndexById(String id) {
        String index = "tb_disable_date_diary_comment";
        restClient.DeleteDocument(index,id);
    }

    //从索引库中删除动态的所有回复
    public void deleteRepliesFromIndexById(String id) {
        String index = "tb_disable_date_diary_reply";
        restClient.DeleteDocument(index,id);
    }

    //从数据库中删除一条评论
    public void deleteCommmentByCommenId(String commentId) {
        commentMapper.deleteCommmentByCommenId(commentId);
    }

    //从数据库中删除一条回复
    public void deleteReplyByReplyId(String replyId) {
        commentMapper.deleteReplyByReplyId(replyId);
    }

    //评论点赞量-1
    public void subCommentLikeAmount(String commentId) {
        commentMapper.subCommentLikeAmount(commentId);
    }

    //评论点赞量+1
    public void addCommentLikeAmount(String commentId) {
        commentMapper.addCommentLikeAmount(commentId);
    }

    //获取所有的回复
    public List<Reply> getAllReplies() {
        return commentMapper.getAllReplies();
    }

    //回复点赞量-1
    public void subReplyLikeAmount(String replyId) {
        commentMapper.subReplyLikeAmount(replyId);
    }

    //回复点赞量+1
    public void addReplyLikeAmount(String replyId) {
        commentMapper.addReplyLikeAmount(replyId);
    }

    //从数据库中查询动态的所有评论
    public List<Comment> getCommentByDiaryId(String diaryId) {
        return commentMapper.getCommentByDiaryId(diaryId);
    }

    //从数据库中查找出所有动态的回复
    public List<Reply> getRepliesByDiaryId(String diaryId) {
        return commentMapper.getRepliesByDiaryId(diaryId);
    }

}
