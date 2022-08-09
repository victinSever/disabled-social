package com.life.admin.service;

import com.life.admin.pojo.LoginILogs;
import com.life.admin.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IUserService extends IService<User> {


    //获取用户信息列表
    List<User> getUserList(Integer index, Integer size);

    //查询用户登录信息
    List<LoginILogs> getLoginLogs(Integer index,Integer size);

    //获取登录日志的总页数
    Integer getPages();

    void deleteUserByUserId(Integer id);
}
