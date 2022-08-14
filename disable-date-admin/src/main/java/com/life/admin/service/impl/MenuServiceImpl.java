package com.life.admin.service.impl;

import com.life.admin.dao.MenuMapper;
import com.life.admin.pojo.Menuchlidren;
import com.life.admin.pojo.Menumoudle;
import com.life.admin.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menumoudle> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    //通过菜单模块名字来查找它的菜单孩子
    public List<Menuchlidren> getMenuchlidren(int parentId) {
        List<Menuchlidren> list = menuMapper.getMenuchlidren(parentId);
        return list;
    }

    //更改是否列出的状态
    public void updateStatus(Integer id,Integer permission) {
        menuMapper.updateStatus(id,permission);
    }
}
