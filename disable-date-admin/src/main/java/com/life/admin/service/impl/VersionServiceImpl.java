package com.life.admin.service.impl;


import com.life.admin.service.IVersionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.life.admin.dao.VersionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.life.admin.pojo.Version;

import java.util.List;

@Service
public class VersionServiceImpl extends ServiceImpl<VersionMapper, Version> implements IVersionService {

    @Autowired
    private VersionMapper versionMapper;

    //获取版本信息
    public List<Version> getVersion(Integer start, Integer size) {
        return versionMapper.getVersion(start,size);
    }
}
