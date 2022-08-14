package com.life.admin.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * CREATE TABLE `tb_disable_date_admin_advertising_place_manage` (
 *   `id` INT NOT NULL AUTO_INCREMENT,
 *   `description` VARCHAR(30) DEFAULT NULL COMMENT '广告位描述',
 *   `palce` VARCHAR(30) DEFAULT NULL COMMENT '广告位位置',
 *   `type` VARCHAR(30) DEFAULT NULL COMMENT '栏目类型',
 *   `status` INT DEFAULT NULL COMMENT '栏目状态 0-未使用 1-在使用',
 *   `size` VARCHAR(20) DEFAULT NULL COMMENT '广告位大小',
 *   `number` INT DEFAULT NULL COMMENT '序号',
 *   `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
 *   `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
 *   `is_delete` INT DEFAULT NULL COMMENT '删除 0—否 1-是',
 *   PRIMARY KEY (`id`)
 * ) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 */
@Data
@TableName("tb_disable_date_admin_advertising_place_manage")
public class Advertiseplace {
    private Integer id;
    private String name;
    private String description;
    private Integer use_status;   //广告使用状态 0-未使用 1-使用
    private Integer type;
    private String advertisingName;      //已插入的广告名称
    private Integer advertisingId;       //已插入的广告id 0-没有 非0-有
    private Integer advertisingStatus;   //广告的状态 0—待使用 1-使用
    private Integer number;              //序号
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
