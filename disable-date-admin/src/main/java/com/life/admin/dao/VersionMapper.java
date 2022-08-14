package com.life.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.life.admin.pojo.Version;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface VersionMapper extends BaseMapper<Version> {

    //获取版本信息
    @Select("select * from tb_disable_date_app_version order by release_time limit #{start},#{size}")
    List<Version> getVersion(@Param("start") Integer start, @Param("size") Integer size);
}
