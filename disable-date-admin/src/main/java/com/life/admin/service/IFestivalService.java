package com.life.admin.service;

import com.life.admin.pojo.Festival;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IFestivalService extends IService<Festival> {

    //获取节日的列表（按照序号进行升序查询）
    List<Festival> getFestivalList(Integer index, Integer size);
}
