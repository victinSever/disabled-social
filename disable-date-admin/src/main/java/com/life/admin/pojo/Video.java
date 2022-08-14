package com.life.admin.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;

/**
 * CREATE TABLE `tb_disable_diary_videos`  (
 *   `id` VARCHAR(64)
 *   `user_id` BIGINT(0)                '发布者id',
 *   `audio_id` VARCHAR(20)             '用户使用音频的信息',
 *   `video_desc` VARCHAR(128)          '视频描述',
 *   `video_path` VARCHAR(100)          '视频存放的路径',
 *   `video_seconds` FLOAT(6, 2)         '视频秒数',
 *   `video_width` INT(0)                '视频宽度',
 *   `video_height` INT(0)               '视频高度',
 *   `cover_path` VARCHAR(100)          '视频封面图  路径',
 *   `like_counts` BIGINT(0)            '喜欢/赞美的数量',
 *   `status` INT(0)                    '视频状态：1-发布成功 2-未过审，管理员操作',
 *   `create_time` DATETIME(0)           '创建时间',
 */
@Data
@TableName("tb_disable_diary_videos")
public class Video {

    private String id;
    private String diaryId;
    private String userId;             //发布者id
    private String audioId;
    private String videoDesc;
    private String videoPath;
    private Integer likeCounts;
    private Integer status;
    private String coverPath;
    private float videoSeconds;
    private Integer videoWidth;
    private Integer videoHeight;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

}
