package com.life.admin.service.impl;

import com.life.admin.dao.AdministratorMapper;
import com.life.admin.pojo.Administrator;
import com.life.admin.service.AdministratorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorServiceImpl extends ServiceImpl<AdministratorMapper, Administrator> implements AdministratorService {

    @Autowired
    private AdministratorMapper administratorMapper;

    //获取用户队列
    public List<Administrator> getAdministratorList(Integer start, Integer size) {
        List<Administrator> list = administratorMapper.getAdministratorList(start,size);
        return list;
    }

    //重置密码
    public void resetPassword(Integer id) {
        String password = "12345678";
        administratorMapper.resetPassword(id,password);
    }

    // 修改管理员电话
    public void changePhone(Integer id, String phone) {
        administratorMapper.changePhone( id,  phone);
    }

    //修改管理员邮箱
    public void changeEmail(Integer id, String email) {
        administratorMapper.changeEmail( id,  email);
    }

    //修改信息
    public void changeInfo(Integer id, String userName, String phone, String email, String introduction, String tab) {
        administratorMapper.changeInfo( id,  userName,  phone,  email,  introduction,  tab);
    }

    //注册管理员
    public void insertAdministrator(Integer id,String account, String userName, String password, String department, String role) {
        administratorMapper.insertAdministrator( id, account,  userName,  password,  department,  role);
    }

    //根据用户名查找管理员
    public Administrator getAdministratorByUserName(String userName) {
        return administratorMapper.getAdministratorByUserName(userName);
    }
}
