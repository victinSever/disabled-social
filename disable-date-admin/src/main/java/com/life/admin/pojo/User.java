package com.life.admin.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 用户
 * CREATE TABLE `tb_disable_date_user` (
 *   `user_id` VARCHAR(11) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '用户主键id',
 *   `nick_name` VARCHAR(20) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户昵称',
 *   `login_name` VARCHAR(11) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '登陆名称(默认为手机号)',
 *   `age` INT DEFAULT NULL COMMENT '年龄',
 *   `password_md5` VARCHAR(32) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT 'MD5加密后的密码',
 *   `is_deleted` TINYINT NOT NULL DEFAULT '0' COMMENT '注销标识字段(1-正常 2-未认证 3-已注销)',
 *   `locked_flag` TINYINT NOT NULL DEFAULT '0' COMMENT '锁定标识字段(1-未锁定 2-已锁定)',
 *   `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
 *   `head_pic_path` VARCHAR(100) DEFAULT NULL COMMENT '头像路劲',
 *   `online_time` VARCHAR(10) DEFAULT NULL COMMENT '上网时间',
 *   `role` VARCHAR(20) DEFAULT NULL COMMENT '角色',
 *   `vip` INT DEFAULT NULL COMMENT 'vip 0-无特权 1-vip',
 *   `audit_status` INT DEFAULT NULL COMMENT '认证状态 0-不通过 1-通过',
 *   `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '跟新时间',
 *   PRIMARY KEY (`user_id`) USING BTREE
 * ) ENGINE=INNODB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
 */
@Data
@TableName("tb_disable_date_user")
public class User {

    private  String userId;
    private String nickName;
    private String loginName;
    private Integer age;
    private String passwordMd5;
    private Integer sex;
    private Integer isVip;
    private Integer sorts;  //积分
    private String disableNumber;
    private String headPicPath;
    private Integer isDeleted;
    private Integer lockedFlag;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private String onlineTime;
//    private Integer auditStatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date deleteTime;
}
