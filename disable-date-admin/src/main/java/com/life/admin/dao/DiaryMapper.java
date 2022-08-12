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

    //获取未审核通过的视频类动态数目
    @Select("select * from tb_disable_date_diary WHERE diary_status = 1 and diary_kind = 2")
    List<Diary> getVideoDiarys();

    //获取待审核的图文类动态数目
    @Select("select * from tb_disable_date_diary WHERE diary_status = 1 and diary_kind = 1")
    List<Diary> getNotVideoDiarys();

    //更新动态审核结果
    @Update("update tb_disable_date_diary SET is_deleted = #{is_deleted},ps = #{ps} WHERE diary_id = #{diaryId} ")
    void updateAuditDiaryResult(@Param("diaryId") String diaryId, @Param("is_deleted") Integer is_deleted,@Param("ps") String ps);

    //更新举报状态
    @Update("update tb_disable_users_report SET status = #{status}, ps = #{ps} WHERE id = #{id}")
    void reportResult(@Param("id") Integer id,@Param("status") Integer status,@Param("ps")String ps);
}
