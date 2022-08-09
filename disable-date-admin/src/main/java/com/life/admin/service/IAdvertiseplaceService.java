package com.life.admin.service;

import com.life.admin.pojo.Advertiseplace;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IAdvertiseplaceService extends IService<Advertiseplace> {

    //获取广告位的列表
    List<Advertiseplace> getAdvertiseplaceList(Integer index, Integer size);
}
