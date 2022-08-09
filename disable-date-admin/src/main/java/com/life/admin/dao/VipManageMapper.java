package com.life.admin.dao;

import com.life.admin.pojo.VipManage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface VipManageMapper extends BaseMapper<VipManage> {

    //修改特权管理的vip功能开放状态
    @Update("update tb_disable_date_admin_vip_manage set status = #{status} WHERE function_name = #{name}")
    void changeVipStatus(@Param("name") String name, @Param("status") Integer status);
}
