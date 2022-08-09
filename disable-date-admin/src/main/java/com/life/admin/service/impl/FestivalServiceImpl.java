package com.life.admin.service.impl;

import com.life.admin.dao.FestivalMapper;
import com.life.admin.pojo.Festival;
import com.life.admin.service.IFestivalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FestivalServiceImpl extends ServiceImpl<FestivalMapper, Festival> implements IFestivalService {

    @Autowired
    private FestivalMapper festivalMapper;

    //获取节日的列表（按照序号进行升序查询）
    public List<Festival> getFestivalList(Integer index, Integer size) {
        List<Festival> list = festivalMapper.getFestivalList( index,  size);
        return list;
    }
}
