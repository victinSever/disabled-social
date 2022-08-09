package com.life.admin.service;

import com.life.admin.pojo.Diary;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IDiaryService extends IService<Diary> {

    //获取（没有审核通过的）动态列表
    List<Diary> getVideoDiaryList(Integer index, Integer size);

    //获取（待审核的非视频类的）动态列表
    List<Diary> getNotVideoDiaryList(Integer index, Integer size);
}
