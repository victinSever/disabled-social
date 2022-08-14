package com.life.admin.service;


import com.life.admin.pojo.VipManage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface VipManageService extends IService<VipManage> {

    //修改特权管理的vip功能开放状态
    void changeVipStatus(String name, Integer status);
}
