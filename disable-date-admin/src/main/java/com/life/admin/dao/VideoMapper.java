package com.life.admin.dao;

import com.life.admin.pojo.Video;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface VideoMapper extends BaseMapper<Video> {

    //找到视频动态的视频链接
    @Select("select * from tb_disable_diary_videos where diary_id = #{diaryId}")
    Video getVideosBydiaryId(String diaryId);
}
