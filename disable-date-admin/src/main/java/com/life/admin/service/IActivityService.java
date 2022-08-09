package com.life.admin.service;

import com.life.admin.pojo.Activity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IActivityService extends IService<Activity> {

    //获取活动的列表（按照序号进行升序查询）
    List<Activity> getActivityList(Integer index, Integer size);
}
