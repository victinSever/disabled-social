package com.life.admin.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * APP版本
 * CREATE TABLE `tb_disable_date_app_version` (
 *   `id` INT NOT NULL AUTO_INCREMENT COMMENT '版本id',
 *   `version` VARCHAR(10) DEFAULT NULL COMMENT '版本号',
 *   `descrip` VARCHAR(100) DEFAULT NULL COMMENT '版本描述',
 *   `release_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
 *   PRIMARY KEY (`id`)
 * ) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 */
@Data
@TableName("tb_disable_date_app_version")
public class Version {

    private Integer id;
    private String version;
    private String descrip;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date releaseTime;

}
