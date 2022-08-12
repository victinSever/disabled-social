package com.life.admin.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**#评论（视频秀、动态）
 *
 *DROP TABLE IF EXISTS `tb_disable_date_diary_comment`;
 * CREATE TABLE `tb_disable_date_diary_comment`  (
 *    `id` BIGINT(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
 *   `comment_id` VARCHAR(60)                        '评论主键id',
 *   `diary_id` BIGINT(0)                           '关联的diary主键',     #视频秀 or 动态 的id
 *   `commentator_name` VARCHAR(20)                 '评论者昵称',
 *   `comment_body` VARCHAR(200)                    '评论内容',
 *   `comment_create_time` DATETIME(0)              '评论提交时间',
 *   `commentator_ip` VARCHAR(20)                   '评论时的ip地址',
 *   `reply_amount` BIGINT(0)  '本条评论的回复数量',
 *   `reply_body` VARCHAR(200)                      '回复内容',
 *   `reply_create_time` DATETIME(0)                '回复时间',
 *   `comment_status` TINYINT(0)                    '是否审核通过 1-未审核 2-审核通过',
 *   `is_deleted` TINYINT(0)                        '是否删除 1-未删除 2-已删除',
 *   PRIMARY KEY (`comment_id`) USING BTREE
 */
@Data
@TableName("tb_disable_date_diary_comment")
public class Comment {

    private Integer id;
    private String commentId;
    private String diaryId;
    private String commentatorName;
    private String commentBody;
    @JsonFormat(pattern = "yyyy年MM月dd日 HH时mm分ss秒", timezone = "GMT+8")
    private Date commentCreateTime;
    private String commentatorIp;
    private Integer replyAmount;
    private Integer commentStatus;
    private Integer isDeleted;   //'是否删除 1-未删除 2-已删除',
    private Integer likeAmount; //点赞量

}
