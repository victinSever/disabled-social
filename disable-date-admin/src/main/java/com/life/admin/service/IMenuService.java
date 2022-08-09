package com.life.admin.service;

import com.life.admin.pojo.Menuchlidren;
import com.life.admin.pojo.Menumoudle;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IMenuService extends IService<Menumoudle> {

    //通过菜单模块名字来查找它的菜单孩子
    List<Menuchlidren> getMenuchlidren(int parentId);

    //更改是否列出的状态
    void updateStatus(Integer id,Integer permission);
}
