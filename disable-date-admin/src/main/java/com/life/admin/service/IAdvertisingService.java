package com.life.admin.service;

import com.life.admin.pojo.Advertising;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IAdvertisingService extends IService<Advertising> {

    //获取广告的列表
    List<Advertising> getAdvertisingList(Integer index, Integer size);
}
