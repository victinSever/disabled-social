package com.life.admin.dao;

import com.life.admin.pojo.Childrentypes;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChildrentypesMapper extends BaseMapper<Childrentypes> {

    //获取下滑栏的孩子
    @Select("select * from tb_disable_date_admin_childrentypes WHERE parent_name = #{type}")
    List<Childrentypes> getChildrenByParentName(String type);
}
