package com.life.admin.service.impl;


import com.life.admin.dao.VideoMapper;
import com.life.admin.pojo.Video;
import com.life.admin.service.IVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements IVideoService {

    @Autowired
    private VideoMapper videoMapper;

    //找到视频动态的视频链接
    public Video getVideosBydiaryId(String diaryId) {
        return videoMapper.getVideosBydiaryId(diaryId);
    }
}