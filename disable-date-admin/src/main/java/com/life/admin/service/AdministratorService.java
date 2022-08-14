package com.life.admin.service;

import com.life.admin.pojo.Administrator;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface AdministratorService extends IService<Administrator> {


    //获取用户队列
    List<Administrator> getAdministratorList(Integer start, Integer size);

    //重置密码
    void resetPassword(Integer id);

    // 修改管理员电话
    void changePhone(Integer id, String phone);

    //修改管理员邮箱
    void changeEmail(Integer id, String email);

    //修改信息
    void changeInfo(Integer id, String userName, String phone, String email, String introduction, String tab);

    //注册管理员
    void insertAdministrator(Integer id,String account, String userName, String password, String department, String role);

    //根据用户名查找管理员
    Administrator getAdministratorByUserName(String userName);
}
