package com.life.admin.service.impl;

import com.life.admin.dao.ChildrentypesMapper;
import com.life.admin.pojo.Childrentypes;
import com.life.admin.service.ChildrentypesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChildrentypesServiceImpl extends ServiceImpl<ChildrentypesMapper, Childrentypes> implements ChildrentypesService {

    @Autowired
    private ChildrentypesMapper childrentypesMapper;

    //获取下滑栏的孩子
    public List<Childrentypes> getChildrenByParentName(String type) {
        List<Childrentypes> list = childrentypesMapper.getChildrenByParentName(type);
        return list;
    }
}
