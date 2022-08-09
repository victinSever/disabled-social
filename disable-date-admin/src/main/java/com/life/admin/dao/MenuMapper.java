package com.life.admin.dao;

import com.life.admin.pojo.Menuchlidren;
import com.life.admin.pojo.Menumoudle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menumoudle> {

    //通过菜单模块名字来查找它的菜单孩子
    @Select("select * from tb_disable_date_admin_menu_children WHERE parent_id = #{parentId}")
    List<Menuchlidren> getMenuchlidren(int parentId);

    //更改是否列出的状态
    @Update("update tb_disable_date_admin_menu_children set permission = #{permission} WHERE id = #{id}")
    void updateStatus(@Param("id") Integer id, @Param("permission") Integer permission);
}
