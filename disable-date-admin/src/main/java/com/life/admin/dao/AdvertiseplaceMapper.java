package com.life.admin.dao;

import com.life.admin.pojo.Advertiseplace;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdvertiseplaceMapper extends BaseMapper<Advertiseplace> {
    //获取广告位的列表
    @Select("select * from tb_disable_date_admin_advertising_place_manage ORDER by number ASC limit #{index},#{size}")
    List<Advertiseplace> getAdvertiseplaceList(@Param("index") Integer index, @Param("size") Integer size);
}
