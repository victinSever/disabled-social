package com.life.admin.service;


import com.life.admin.pojo.Video;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IVideoService extends IService<Video> {

    //找到视频动态的视频链接
    Video getVideosBydiaryId(String diaryId);
}
