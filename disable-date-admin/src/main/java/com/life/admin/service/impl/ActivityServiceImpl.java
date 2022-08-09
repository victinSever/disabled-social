package com.life.admin.service.impl;

import com.life.admin.dao.ActivityMapper;
import com.life.admin.pojo.Activity;
import com.life.admin.service.IActivityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements IActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    //获取活动的列表（按照序号进行升序查询）
    public List<Activity> getActivityList(Integer index, Integer size) {
        List<Activity> list = activityMapper.getActivityList( index,  size);
        return list;
    }
}
