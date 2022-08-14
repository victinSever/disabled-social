package com.life.admin.service.impl;

import com.life.admin.dao.PictureMapper;
import com.life.admin.pojo.Picture;
import com.life.admin.service.IPictureService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PictureServiceImpl extends ServiceImpl<PictureMapper, Picture> implements IPictureService {

    @Autowired
    private PictureMapper pictureMapper;

    @Autowired
    private RestHighLevelClient client;

    @Override
    public List<String> getPathById(String name, String id) throws IOException {
        return null;
    }


    //根据动态dirayId从数据库中查找出动态的所有配图
    public List<Picture> getPicturesByDiaryId(String diaryId) {
        return pictureMapper.getAllPictureBydiaryId(diaryId);
    }

}
