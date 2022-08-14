package com.life.admin.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * CREATE TABLE `tb_disable_date_admin_vip_manage` (
 *   `id` INT NOT NULL AUTO_INCREMENT COMMENT '权力id',
 *   `function_name` VARCHAR(50) DEFAULT NULL COMMENT '功能名称',
 *   `status` TINYINT(1) DEFAULT '0' COMMENT 'vip功能的特权限制状态 true-开发 false-不开放',
 *   `longto` INT DEFAULT NULL COMMENT '属于哪个模块 1-约吧中心 2-广场中心 3-消息中心 4-个人中心',
 *   PRIMARY KEY (`id`)
 * ) ENGINE=INNODB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 */
@Data
@TableName("tb_disable_date_admin_vip_manage")
public class VipManage{
    private Integer id;
    private String functionName;
    private Integer status;
    private Integer longto;
}
