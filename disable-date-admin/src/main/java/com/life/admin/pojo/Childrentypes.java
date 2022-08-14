package com.life.admin.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 类似下拉框的数据存储
 * CREATE TABLE `tb_disable_date_admin_childrentypes` (
 *   `id` INT NOT NULL AUTO_INCREMENT COMMENT '内容的id',
 *   `parent_name` VARCHAR(30) DEFAULT NULL COMMENT '关联（父级）名称',
 *   `name` VARCHAR(20) DEFAULT NULL COMMENT '名称',
 *   PRIMARY KEY (`id`)
 * ) ENGINE=INNODB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 */
@Data
@TableName("tb_disable_date_admin_childrentypes")
public class Childrentypes {
    private Integer id;
    private String parentName;
    private String name;
    private Integer nameId;
}
