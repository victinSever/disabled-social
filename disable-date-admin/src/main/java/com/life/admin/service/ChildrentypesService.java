package com.life.admin.service;

import com.life.admin.pojo.Childrentypes;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ChildrentypesService extends IService<Childrentypes> {

    List<Childrentypes> getChildrenByParentName(String type);
}
