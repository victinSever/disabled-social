package com.life.admin.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * VIP充值
 * CREATE TABLE `tb_disable_date_admin_vip_recharge` (
 *   `id` INT NOT NULL AUTO_INCREMENT,
 *   `order_id` VARCHAR(60) DEFAULT NULL COMMENT '订单ID',
 *   `order_status` INT DEFAULT NULL COMMENT '订单状态 0-支付失败 1-支付成功 2-待支付',
 *   `type` VARCHAR(20) DEFAULT NULL COMMENT 'VIP类型',
 *   `price` DOUBLE DEFAULT NULL COMMENT '价格',
 *   `start_time` DATETIME DEFAULT NULL COMMENT '特权开始时间',
 *   `end_time` DATETIME DEFAULT NULL COMMENT '特权结束时间',
 *   `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
 *   `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
 *   PRIMARY KEY (`id`)
 * ) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 */
@Data
@TableName("tb_disable_date_admin_vip_recharge")
public class VIPrecharge {
    private Integer id;
    private String orderId;
    private Integer orderStatus;
    private String type;
    private double price;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
