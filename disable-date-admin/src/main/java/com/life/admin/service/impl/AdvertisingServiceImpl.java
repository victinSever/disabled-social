package com.life.admin.service.impl;

import com.life.admin.dao.AdvertisingMapper;
import com.life.admin.pojo.Advertising;
import com.life.admin.service.IAdvertisingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertisingServiceImpl extends ServiceImpl<AdvertisingMapper, Advertising> implements IAdvertisingService {

    @Autowired
    private AdvertisingMapper advertisingMapper;

    //获取广告的列表
    public List<Advertising> getAdvertisingList(Integer index, Integer size) {
        List<Advertising> list = advertisingMapper.getAdvertisingList( index,  size);
        return list;
    }
}
