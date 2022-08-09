package com.life.admin.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 菜单模块
 * CREATE TABLE `tb_disable_date_admin_menu_module` (
 *   `id` INT NOT NULL AUTO_INCREMENT COMMENT '菜单id',
 *   `name` VARCHAR(20) DEFAULT NULL COMMENT '菜单名称',
 *   `type` INT DEFAULT '1' COMMENT '菜单类型 1-模块',
 *   `route_address` VARCHAR(50) DEFAULT NULL COMMENT '路由地址',
 *   `permissions` INT DEFAULT '1' COMMENT '权限标识 1-减 2-加',
 *   `status` INT DEFAULT '1' COMMENT '状态 0-异常 1-正常',
 *   `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
 *   `upadte_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
 *   `is_delete` INT DEFAULT '0' COMMENT '删除 0-否 1-是',
 *   PRIMARY KEY (`id`)
 * ) ENGINE=INNODB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 */
@Data
@TableName("tb_disable_date_admin_menu_module")
public class Menumoudle {
    private Integer id;
    private String name;
    private Integer type;
    private String routeAddress;
    private Integer permissions;
    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    private Integer isDelete;
}
