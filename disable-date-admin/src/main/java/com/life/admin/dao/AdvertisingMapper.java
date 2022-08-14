package com.life.admin.dao;

import com.life.admin.pojo.Advertising;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdvertisingMapper extends BaseMapper<Advertising> {

    //获取广告的列表
    @Select("select * from tb_disable_date_admin_advertising ORDER by ordinal ASC limit #{index},#{size}")
    List<Advertising> getAdvertisingList(@Param("index") Integer index,@Param("size") Integer size);
}
