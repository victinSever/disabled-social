package com.life.admin.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * CREATE TABLE `tb_disable_date_diary_reply`  (			#评论的恢复信息表
 *   `id` INT(0) NOT NULL AUTO_INCREMENT ,
 *   `reply_id` VARCHAR(60)              '回复的id',
 *   `comment_id` VARCHAR(60)            '评论的id',
 *   `diary_id` VARCHAR(60)              '动态的id',
 *   `reply_content` VARCHAR(11)         '回复者id',
 *   `reply_user_id` VARCHAR(11)         '回复者id',
 *   `reply_user_name` VARCHAR(11)       '回复者的名称',
 *   `comment_user_id` VARCHAR(11)       '被恢复的评论的用户id',
 *   `comment_user_name` VARCHAR(11)     '被恢复的评论的用户名称',
 *   `reply_status` TINYINT(0)           '是否审核通过 0-审核不通过 1-审核通过 2-未审核' ,
 *   `create_time` DATETIME(0)           '创建时间',
 *   PRIMARY KEY (`id`) USING BTREE
 * ) ENGINE = INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;
 */

@Data
@TableName("tb_disable_date_diary_reply")
public class Reply {

    private Integer id;
    private String  replyId;
    private String commentId;
    private String diaryId;
    private String replyContent;
    private String replyUserId;
    private String replyUserName;
    private String commentUserId;
    private String commentUserName;
    private Integer replyStatus;
    @JsonFormat(pattern = "yyyy年MM月dd日 HH时mm分ss秒", timezone = "GMT+8")
    private Date createTime;
    private Integer likeAmount; //点赞量
}
