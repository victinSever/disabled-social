package com.life.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.life.admin.pojo.Version;

import java.util.List;

public interface IVersionService extends IService<Version> {

    //获取版本信息
    List<Version> getVersion(Integer start, Integer size);
}
