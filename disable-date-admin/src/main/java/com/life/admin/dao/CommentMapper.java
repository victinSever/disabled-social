package com.life.admin.dao;

import com.life.admin.pojo.Comment;
import com.life.admin.pojo.Reply;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CommentMapper {

    //查询全部
    @Select("select * from tb_disable_date_diary_comment")
    List<Comment> getAll();

    //根据commenId找出评论
    @Select("select * from tb_disable_date_diary_comment where comment_id = #{commentId}")
    Comment getCommentById(String commentId);

    //根据replyId找出回复并新增到索引库
    @Select("select * from tb_disable_date_diary_reply where reply_id = #{replyId}")
    Reply getReplyById(String replyId);

//    @Update("UPDATE tb_disable_date_diary SET diary_comment = diary_comment+1 WHERE diary_id = #{diary_id}")
    //给对应的动态的回复量 +1
    @Update("UPDATE tb_disable_date_diary_comment SET reply_amount = reply_amount + 1 WHERE comment_id = #{comment_id}")
    void commentReplyAmountAddOne(String comment_id);

    //从数据库中删除动态的所有评论
    @Delete("delete from tb_disable_date_diary_comment WHERE diary_id = #{diaryId}")
    void deleteComments(String diaryId);

    //从数据库中删除动态的所有评论的所有回复
    @Delete("delete from tb_disable_date_diary_reply WHERE diary_id = #{diaryId}")
    void deleteReplies(String diaryId);

    //从数据库中查询动态的所有评论
    @Select("select * from tb_disable_date_diary_comment WHERE diary_id = #{diaryId}")
    List<Comment> getCommentByDiaryId(String diaryId);

    //从数据库中查询动态的所有回复
    @Select("select * from tb_disable_date_diary_reply WHERE diary_id = #{diaryId}")
    List<Reply> getRepliesByDiaryId(String diaryId);

    //从数据库中删除一条评论
    @Delete("delete from tb_disable_date_diary_comment WHERE comment_id = #{commentId}")
    void deleteCommmentByCommenId(String commentId);

    //从数据库中删除一条回复
    @Delete("delete from tb_disable_date_diary_reply WHERE reply_id = #{replyId}")
    void deleteReplyByReplyId(String replyId);

    //评论点赞量-1
    @Update("UPDATE tb_disable_date_diary_comment SET like_amount = like_amount - 1 WHERE comment_id = #{comment_id}")
    void subCommentLikeAmount(String commentId);

    //评论点赞量+1
    @Update("UPDATE tb_disable_date_diary_comment SET like_amount = like_amount + 1 WHERE comment_id = #{comment_id}")
    void addCommentLikeAmount(String commentId);

    //获取所有的回复
    @Select("select * from tb_disable_date_diary_reply")
    List<Reply> getAllReplies();

    //回复点赞量-1
    @Update("UPDATE tb_disable_date_diary_reply SET like_amount = like_amount - 1 WHERE reply_id = #{replyId}")
    void subReplyLikeAmount(String replyId);

    //回复点赞量+1
    @Update("UPDATE tb_disable_date_diary_reply SET like_amount = like_amount + 1 WHERE reply_id = #{replyId}")
    void addReplyLikeAmount(String replyId);
}
