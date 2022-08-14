package com.life.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 广告
 CREATE TABLE `tb_disable_date_admin_advertising` (
 `id` INT NOT NULL AUTO_INCREMENT,
 `ordinal` INT DEFAULT NULL COMMENT '序号',
 `title` VARCHAR(100) DEFAULT NULL COMMENT '广告标题',
 `unit` VARCHAR(50) DEFAULT NULL COMMENT '所属广告单位',
 `type` VARCHAR(30) DEFAULT NULL COMMENT '广告类型',
 `appearence_path` VARCHAR(50) DEFAULT NULL COMMENT '广告封面途径',
 `status` INT DEFAULT NULL COMMENT '广告状态 0-禁用 1-正常',
 `advertising_links` VARCHAR(50) DEFAULT NULL COMMENT '广告链接',
 `advertised_model` VARCHAR(20) DEFAULT NULL COMMENT '广告型号',
 `view_amount` BIGINT DEFAULT NULL COMMENT '浏览量',
 `start_time` DATETIME DEFAULT NULL COMMENT '开始时间',
 `end_time` DATETIME DEFAULT NULL COMMENT '结束时间',
 `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
 `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
 PRIMARY KEY (`id`)
 ) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 */
@Data
@TableName("tb_disable_date_admin_advertising")
public class Advertising {

//    @TableId(value = "id",type = IdType.ID_WORKER_STR")
    private Integer id;
    private Integer ordinal;
    private String title;
    private String place;
    private String descrip;
    private Integer status;
    private String appearencePath;
    private String advertisingLinks;
    private String advertisedModel;
    private Integer viewAmount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

}
