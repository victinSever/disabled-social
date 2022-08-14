package com.life.admin.dao;

import com.life.admin.pojo.Festival;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FestivalMapper extends BaseMapper<Festival> {

    //获取节日的列表（按照序号进行升序查询）
    @Select("select * from tb_disable_date_admin_festival limit #{index},#{size}")
    List<Festival> getFestivalList(@Param("index") Integer index,@Param("size") Integer size);
}
