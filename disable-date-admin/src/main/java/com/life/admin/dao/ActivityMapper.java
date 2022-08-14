package com.life.admin.dao;

import com.life.admin.pojo.Activity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ActivityMapper extends BaseMapper<Activity> {

    //获取活动的列表（按照序号进行升序查询）
    @Select("select * from tb_disable_date_admin_activity ORDER by number ASC limit #{index},#{size}")
    List<Activity> getActivityList(@Param("index") Integer index, @Param("size") Integer size);
}
