package com.life.admin.service.impl;

import com.life.admin.dao.VipManageMapper;
import com.life.admin.pojo.VipManage;
import com.life.admin.service.VipManageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VipManageServiceImpl extends ServiceImpl<VipManageMapper, VipManage> implements VipManageService {

    @Autowired
    private VipManageMapper vipManageMapper;

    //修改特权管理的vip功能开放状态
    public void changeVipStatus(String name, Integer status) {
        vipManageMapper.changeVipStatus( name,  status);
    }
}
