package com.life.admin.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * DROP TABLE IF EXISTS `tb_disable_date_diary`;   #动态（广场模块的）
 * CREATE TABLE `tb_disable_date_diary`  (
 *   `id` BIGINT(0) NOT NULL AUTO_INCREMENT COMMENT ,
 *   `diary_id` BIGINT(0)  '朋友圈记录主键id',
 *   `diary_user_id` VARCHAR(11)  '发表朋友圈的用户id',
 *   `diary_user_name` VARCHAR(20)  '用户名称',
 *   `diary_title` VARCHAR(200)  '朋友圈标题',
 *   `diary_content` MEDIUMTEXT  '朋友圈内容',
 *   `diary_category_id` INT(0)  '主题分类id ',
 *   `diary_category_name` VARCHAR(50)  '朋友圈分类(冗余字段)',
 *   `diary_tags` VARCHAR(200)  '朋友圈标签',
 *   `diary_status` TINYINT(0) '1-草稿 2-发布',
 *   `diary_comment` BIGINT(0) '讨论量',
 *   `diary_views` BIGINT(0)  '阅读量',
 *   `diary_love` BIGINT(0)  '点赞量',
 *   `enable_look` TINYINT(0) '0-不可见 1-可见',
 *   `enable_comment` TINYINT(0)  '1-允许评论 2-不允许评论',
 *   `is_deleted` TINYINT(0)  '是否删除 1-否 2-是',
 *   `create_time` DATETIME(0)  '朋友圈添加时间',
 *   `update_time` DATETIME(0)  '朋友圈修改时间',
 *   ps 审核不通过后返回的提示
 */
@Data
@TableName("tb_disable_date_diary")
public class Diary {

    private Integer id;
    private String diaryId;
    private String diaryUserId;
    private String diaryUserName;
    private String diaryTitle;
    private String diaryContent;
    private Integer diaryKind;         //动态分类id 1-图文动态 2-视频类动态
    private Integer diaryCategoryId;    //主题分类id
    private String diaryCategoryName;
    private String diaryTags;
    private Integer diaryStatus;
    private Integer diaryComment;
    private Integer diaryViews;
    private Integer diaryLove;
    private Integer enableLook;
    private Integer enableComment;
    private  Integer isDeleted;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    private Integer isReport;       //举报 0-不举报 1-举报
    private String ps;              //审核不通过后返回的提示

}
