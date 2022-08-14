package com.life.admin.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * CREATE TABLE `tb_disable_diary_pictures`  (
 *   `id` VARCHAR(64)  'id',
 *   `pic_name` VARCHAR(30) '照片文件的命名',
 *   `diary_id` VARCHAR(30) '图片的来源主键id，来源可以是动态 or 图片秀',    #用UUID来给唯一标识
 *   `user_id` BIGINT(0)                     '发布者id',
 *   `pic_path` VARCHAR(100) '视频存放的路径',
 *   `pic_width` INT(0)                      '图片宽度',
 *   `pic_height` INT(0)                     '图片高度',
 *   `like_counts` BIGINT(0)                 '喜欢/赞美的数量',
 *   `status` INT(0)                         '视频状态：1-发布成功 2-未过审，管理员操作',
 *   `create_time` DATETIME(0)               '创建时间',
 */

@Data
@TableName("tb_disable_diary_pictures")
public class Picture {

    private String id;
    private String picName;
    private String diaryId;
    private String userId;
    private String picPath;
    private Integer picWidth;
    private Integer picHeight;
    private Integer likeCounts;
    private Integer status;

//    private String id;
//    private String pic_name;
//    private String diary_id;
//    private String user_id;
//    private String pic_path;
//    private Integer pic_width;
//    private Integer pic_heigth;
//    private Integer like_counts;
//    private Integer status;

}
