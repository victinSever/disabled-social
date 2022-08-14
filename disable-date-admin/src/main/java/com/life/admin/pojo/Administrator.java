package com.life.admin.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 管理员
 * CREATE TABLE `tb_disable_date_admin_users` (
 *   `id` INT NOT NULL AUTO_INCREMENT COMMENT '管理员的d',
 *   `name` VARCHAR(20) DEFAULT NULL COMMENT '姓名',
 *   `user_name` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
 *    `password` VARCHAR(20) DEFAULT NULL COMMENT '密码',
 *   `sex` INT DEFAULT NULL COMMENT '姓名 1-男 2-女',
 *   `head_pic_path` VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '头像路径',
 *   `phone` VARCHAR(11) DEFAULT NULL COMMENT '电话',
 *   `email` VARCHAR(30) DEFAULT NULL COMMENT '邮箱',
 *   `role` VARCHAR(10) DEFAULT NULL COMMENT '角色',
 *   `department` VARCHAR(10) DEFAULT NULL COMMENT '部门',
 *   `online_status` INT DEFAULT NULL COMMENT '职级状态',
 *   `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
 *   `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
 *   `audit_status` INT DEFAULT NULL COMMENT '审核状态 0-不通过 1-通过',
 *   `introduction` TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '个人简介',
 *   PRIMARY KEY (`id`)
 * ) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 */
@Data
@TableName("tb_disable_date_admin_users")
public class Administrator {
    private Integer id;
    private  String name;
    private String userName;
    private String password;
    private Integer sex;
    private String  headPicPath;
    private String phone;
    private String email;
    private String role;
    private String department;
    private Integer onlineStatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    private Integer auditStatus;
    private String introduction;
}
