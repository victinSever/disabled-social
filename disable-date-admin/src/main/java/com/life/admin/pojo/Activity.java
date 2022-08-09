package com.life.admin.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 活动
 * CREATE TABLE `tb_diable_date_admin_activity` (
 *   `id` INT NOT NULL AUTO_INCREMENT COMMENT '活动id',
 *   `name` VARCHAR(100) DEFAULT NULL COMMENT '活动名称',
 *   `cover_path` VARCHAR(50) DEFAULT NULL COMMENT '活动封面路径',
 *   `place` VARCHAR(20) DEFAULT NULL COMMENT '活动投放位置',
 *   `activity_status` INT DEFAULT NULL COMMENT '活动状态 0-结束报名 1-报名中',
 *   `concern_amount` BIGINT DEFAULT NULL COMMENT '关注度',
 *   `sign_amount` INT DEFAULT NULL COMMENT '报名人数',
 *   `number` INT DEFAULT NULL COMMENT '序号',
 *   `start_time` DATETIME DEFAULT NULL COMMENT '开始时间',
 *   `end_time` DATETIME DEFAULT NULL COMMENT '结束时间',
 *   `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
 *   `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
 *   PRIMARY KEY (`id`)
 * ) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 */
@Data
@TableName("tb_disable_date_admin_activity")
public class Activity {
    private Integer id;
    private  String name;
    private String coverPath;
    private String place;
    private Integer activityStatus;
    private Integer concernAmount;
    private Integer signAmount;
    private Integer number;
    private Integer openStatus;    //是否开放报名 1-是 0-否
    private Integer releaseStatus; //是否发布了 1-是 0-否
    @JsonFormat(pattern = "yyyy年MM月dd日 HH时mm分ss秒", timezone = "GMT+8")
    private Date startTime;
    @JsonFormat(pattern = "yyyy年MM月dd日 HH时mm分ss秒", timezone = "GMT+8")
    private Date endTime;
    @JsonFormat(pattern = "yyyy年MM月dd日 HH时mm分ss秒", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy年MM月dd日 HH时mm分ss秒", timezone = "GMT+8")
    private Date updateTime;
}
