package com.life.admin.dao;

import com.life.admin.pojo.Administrator;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdministratorMapper extends BaseMapper<Administrator> {

    //获取用户队列
    @Select("select * from tb_disable_date_admin_users order by id limit #{start}, #{size}")
    List<Administrator> getAdministratorList(@Param("start") Integer start, @Param("size") Integer size);

    //重置密码
    @Update("update tb_disable_date_admin_users set password = #{password} WHERE id = #{id}")
    void resetPassword(@Param("id") Integer id,@Param("password") String password);

    // 修改管理员电话
    @Update("update tb_disable_date_admin_users set phone = #{phone} WHERE id = #{id}")
    void changePhone(@Param("id") Integer id,@Param("phone")  String phone);

    //修改管理员邮箱
    @Update("update tb_disable_date_admin_users set email = #{email} WHERE id = #{id}")
    void changeEmail(@Param("id") Integer id,@Param("email")  String email);

    //修改信息
    @Update("update tb_disable_date_admin_users set user_name = #{userName},phone = #{phone},email = #{email}," +
            "introduction = #{introduction},tab = #{tab} WHERE id = #{id}")
    void changeInfo(@Param("id") Integer id,@Param("userName") String userName,@Param("phone") String phone,
                    @Param("email")String email, @Param("introduction")String introduction,@Param("tab") String tab);

    //注册管理员
    @Insert("insert into tb_disable_date_admin_users(id,account,user_name,password,department,role)" +
            " values (#{id},#{account},#{userName},#{password},#{department},#{role})")
    void insertAdministrator(@Param("id") Integer id,@Param("account") String account,@Param("userName") String userName,
                             @Param("password") String password,@Param("department") String department,
                             @Param("role") String role);

    //根据用户名查找管理员
    @Select("select * from tb_disable_date_admin_users WHERE user_name = #{userName}")
    Administrator getAdministratorByUserName(String userName);
}
