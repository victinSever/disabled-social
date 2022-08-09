package com.life.admin.service;

import com.life.admin.pojo.Picture;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.IOException;
import java.util.List;

public interface IPictureService extends IService<Picture> {

    //根据用户id去找图像路径
    List<String> getPathById(String name, String id) throws IOException;

    //从数据库中查找出动态的所有配图
    List<Picture> getPicturesByDiaryId(String diaryId);

}
