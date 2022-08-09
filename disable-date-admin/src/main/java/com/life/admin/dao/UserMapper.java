package com.life.admin.dao;

import com.life.admin.pojo.LoginILogs;
import com.life.admin.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    //获取用户信息列表
    @Select("select * from tb_disable_date_user ORDER by user_id limit #{index},#{size}")
    List<User> getUserList(@Param("index") Integer index,@Param("size") Integer size);

    //查询用户登录信息
    @Select("select * from tb_disable_date_login_logs ORDER by last_time DESC limit #{index},#{size}")
    List<LoginILogs> getLoginLogs(@Param("index") Integer index, @Param("size") Integer size);

    //获取登录日志的总页数
    @Select("select COUNT(*) from tb_disable_date_login_logs")
    Integer  getPages();

    @Delete("delete FROM tb_disable_date_user WHERE user_id = #{id}")
    void deleteUserByUserId(Integer id);
}
