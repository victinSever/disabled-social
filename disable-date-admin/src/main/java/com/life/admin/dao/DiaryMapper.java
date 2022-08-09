package com.life.admin.dao;

import com.life.admin.pojo.Diary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DiaryMapper extends BaseMapper<Diary> {

    //根据id查询diary
    @Select("select * from tb_disable_date_diary where diary_id = #{diaryId}")
    Diary getDiaryById(String diaryId);

    //从数据库中删除动态
    @Delete("delete from tb_disable_date_diary WHERE diary_id = #{diaryId}")
    void deleteDiary(String diaryId);

    //获取（没有审核通过的）视频动态列表
    @Select("select * from tb_disable_date_diary WHERE diary_status = 1 and diary_kind = 2 order by create_time ASC limit #{index},#{size}")
    List<Diary> getVideoDiaryList(@Param("index") Integer index, @Param("size") Integer size);

    //获取（待审核的非视频类的）动态列表
    @Select("select * from tb_disable_date_diary WHERE diary_status = 1 and diary_kind != 2 order by create_time ASC limit #{index},#{size}")
    List<Diary> getNotVideoDiaryList(@Param("index") Integer index, @Param("size") Integer size);
}
