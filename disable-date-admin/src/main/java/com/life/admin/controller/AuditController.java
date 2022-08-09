package com.life.admin.controller;

import com.life.admin.pojo.AuditLogs;
import com.life.admin.pojo.Diary;
import com.life.admin.pojo.Picture;
import com.life.admin.pojo.Video;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.life.admin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 审核模块
 */
@RestController
@RequestMapping("/audit")
public class AuditController {

    @Autowired
    private IVideoService videoService;

    @Autowired
    private IPictureService pictureService;

    @Autowired
    private AuditService auditService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IDiaryService diaryService;

    /**
     * 获取（待审核）视频类动态列表
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/getVideoDiaryList")
    public JSONArray getDiaryList(@RequestParam("page") Integer page, @RequestParam("size") Integer size){
        JSONArray jsonArray = new JSONArray();
        Integer all = diaryService.list().size();
        Integer index = (page - 1) * size;
        List<Diary> diaries = diaryService.getVideoDiaryList(index,size);
        System.out.println(diaries);
        for(Diary diary : diaries){
            JSONObject jsonObject = new JSONObject();
            System.out.println("发布时间-->"+diary.getCreateTime());
            //找照片
            Video video = videoService.getVideosBydiaryId(diary.getDiaryId());
            jsonObject.put("total",all);
            jsonObject.put("diary",diary);
            jsonObject.put("video",video.getVideoPath());
            jsonArray.add(jsonObject);
        }
        System.out.println(jsonArray);
        return jsonArray;
    }

    /**
     * 获取（待审核的非视频类的）动态列表
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/getNotVideoDiaryList")
    public JSONArray getNotVideoDiaryList(@RequestParam("page") Integer page,@RequestParam("size") Integer size){
        JSONArray jsonArray = new JSONArray();
        Integer all = diaryService.list().size();
        Integer index = (page - 1) * size;
        List<Diary> diaries = diaryService.getNotVideoDiaryList(index,size);
        for(Diary diary : diaries){
            JSONObject jsonObject = new JSONObject();
            System.out.println("发布时间-->"+diary.getCreateTime());
            //找照片
            List<Picture> list = pictureService.getPicturesByDiaryId(diary.getDiaryId());
            List<String> picPath = new ArrayList<String>();
            for(Picture picture : list){
                picPath.add(picture.getPicPath());
            }
            jsonObject.put("total",all);
            jsonObject.put("diary",diary);
            jsonObject.put("picture",picPath);
            jsonArray.add(jsonObject);
        }
        System.out.println(jsonArray);
        return jsonArray;
    }

    /**
     * 获取审核日志
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/getAuditLogs")
    public JSONObject getAuditLogs(@RequestParam("page")Integer page,@RequestParam("size")Integer size){
        JSONObject jsonObject = new JSONObject();

        Integer total = auditService.list().size();
        Integer index = (page - 1)*size;
        List<AuditLogs> auditLogs = auditService.getAuditLogs(index,size);
        jsonObject.put("total",total);
        jsonObject.put("AuditLogs",auditLogs);
        System.out.println(total);
        System.out.println(auditLogs);
        return jsonObject;
    }

}
