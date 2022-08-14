package com.life.admin.service.impl;

import com.life.admin.dao.AdvertiseplaceMapper;
import com.life.admin.pojo.Advertiseplace;
import com.life.admin.service.IAdvertiseplaceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertiseplaceImpl extends ServiceImpl<AdvertiseplaceMapper, Advertiseplace> implements IAdvertiseplaceService {

    @Autowired
    private AdvertiseplaceMapper advertiseplaceMapper;

    //获取广告位的列表
    public List<Advertiseplace> getAdvertiseplaceList(Integer index, Integer size) {
        List<Advertiseplace> list = advertiseplaceMapper.getAdvertiseplaceList(index, size);
        return list;
    }
}
