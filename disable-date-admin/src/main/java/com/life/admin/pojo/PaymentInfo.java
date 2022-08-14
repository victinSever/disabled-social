package com.life.admin.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * CREATE TABLE `tb_disable_date_payment_info` (
 *   `order_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '订单id',
 *   `order_sn` CHAR(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '订单号（对外业务号）',
 *   `user_id` VARCHAR(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户id',
 *   `trade_no` VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '支付宝交易流水号',
 *   `vip_type` INT DEFAULT NULL COMMENT 'VIP类别 1-月包 2-季包 3-年包',
 *   `total_amount` DECIMAL(18,4) DEFAULT NULL COMMENT '价格（支付总额）',
 *   `pay_way` INT DEFAULT NULL COMMENT '支付方式 1-微信支付 2-支付宝支付',
 *   `subject` VARCHAR(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '交易内容',
 *   `payment_status` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '订单状态 0-待支付 1-支付成功 2-支付失败',
 *   `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '订单创建时间',
 *   `updateTime` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '订单更新时间',
 *   `start_time` DATETIME DEFAULT NULL COMMENT '特权开始时间',
 *   `end_time` DATETIME DEFAULT NULL COMMENT '特权结束时间',
 *   PRIMARY KEY (`order_id`) USING BTREE,
 *   UNIQUE KEY `order_sn` (`order_sn`) USING BTREE,
 *   UNIQUE KEY `alipay_trade_no` (`trade_no`) USING BTREE
 * ) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='支付信息表';
 */
@Data
@TableName("tb_disable_date_payment_info")
public class PaymentInfo {
    private Integer orderId;
    private String orderSn;
    private String userId;
    private String tradeNo;
    private Integer vipType;
    private double totalAmount;
    private Integer payWay;
    private String subject;
    private String paymentStatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
