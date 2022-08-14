package com.life.admin.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 节日
 * CREATE TABLE `tb_disable_date_admin_festival` (
 *   `id` INT NOT NULL AUTO_INCREMENT,
 *   `festival_name` VARCHAR(20) DEFAULT NULL COMMENT '节日名称',
 *   `topic_name` VARCHAR(20) DEFAULT NULL COMMENT '主题名称',
 *   `topic_color` VARCHAR(20) DEFAULT NULL COMMENT '主题色',
 *   `topic_status` INT DEFAULT NULL COMMENT '主题状态 0-未开始 1-展示中',
 *   `cartoon` INT DEFAULT NULL COMMENT '动画 0-无动画播放 1-展示动画',
 *   `start_time` DATETIME DEFAULT NULL COMMENT '开始时间',
 *   `end_time` DATETIME DEFAULT NULL COMMENT '结束时间',
 *   `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
 *   `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
 *   `is_status` INT DEFAULT '0' COMMENT '删除 0-否 1-是',
 *   PRIMARY KEY (`id`)
 * ) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 */
@Data
@TableName("tb_disable_date_admin_festival")
public class Festival {
    private Integer id;
    private String festivalName;
    private String topicName;
    private Integer topicColor;
    private Integer topicStatus;
    private Integer cartoon;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    private Integer isDelete;
}
