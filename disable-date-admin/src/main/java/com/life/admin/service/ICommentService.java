package com.life.admin.service;


import com.life.admin.pojo.Comment;
import com.life.admin.pojo.Reply;

import java.util.List;

public interface ICommentService {

    //查询全部
    List<Comment> getAll();

    //根据commenId找出评论并新增到索引库
    void insertCommentToIndexById(String commentId);

    //根据replyId找出回复并新增到索引库
    void insertReplyToIndexById(String replyId);

    //根据评论的id 查找其所有的 回复（在索引库中查找）
    List<Reply> getAllReplyByDiaryId(String page, String size, String sortName, String sortOrder, String commentId);

    //从数据库中删除动态的所有评论
    void deleteCommments(String diaryId);

    //从数据库中删除动态的所有评论的所有回复
    void deleteReplies(String diaryId);

    //根据replyId找出回复并新增到索引库
    Reply getReplyById(String replyId);

    //根据commenId找出评论
    Comment getCommentById(String commentId);

    //从索引库中删除评论
    void deleteCommentsFromIndexById(String message);

    //从数据库中查询动态的所有评论
    List<Comment> getCommentByDiaryId(String diaryId);

    //从数据库中查询动态的所有回复
    List<Reply> getRepliesByDiaryId(String diaryId);

    //从索引库中删除动态的所有回复
    void deleteRepliesFromIndexById(String message);

    //从数据库中删除一条评论
    void deleteCommmentByCommenId(String commentId);

    //从数据库中删除一条回复
    void deleteReplyByReplyId(String replyId);

    //评论点赞量-1
    void subCommentLikeAmount(String commentId);

    //评论点赞量+1
    void addCommentLikeAmount(String commentId);

    //获取所有的回复
    List<Reply> getAllReplies();

    //回复点赞量-1
    void subReplyLikeAmount(String replyId);

    //回复点赞量+1
    void addReplyLikeAmount(String replyId);
}
