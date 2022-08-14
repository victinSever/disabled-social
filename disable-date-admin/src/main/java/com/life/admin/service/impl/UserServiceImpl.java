package com.life.admin.service.impl;

import com.life.admin.dao.UserMapper;
import com.life.admin.pojo.LoginILogs;
import com.life.admin.pojo.User;
import com.life.admin.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;


    //获取用户信息列表
    public List<User> getUserList(Integer index, Integer size) {
        List<User> list = userMapper.getUserList( index,  size);
        return list;
    }

    //查询用户登录信息
    public List<LoginILogs> getLoginLogs(Integer index,Integer size) {
        List<LoginILogs> list =  userMapper.getLoginLogs( index,size);
        return list;
    }

    //获取登录日志的总页数
    public Integer getPages() {
        Integer all = userMapper. getPages();
        return all;
    }

    public void  deleteUserByUserId(Integer id) {
        userMapper.deleteUserByUserId(id);
    }

}
