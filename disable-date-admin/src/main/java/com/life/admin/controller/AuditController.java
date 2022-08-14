package com.life.admin.controller;

import com.life.admin.common.R;
import com.life.admin.constants.MqConstants;
import com.life.admin.pojo.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.life.admin.service.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 审核模块
 */
@RestController
@RequestMapping("/audit")
public class AuditController {

    //输入发送消息的API
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ReportService reportService;

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
     * 获取（待审核）视频类动态列表     【注意，后面，记得把获取数量的sql的status改为2，2才是待审核状态】
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/getVideoDiaryList")
    public R getDiaryList(@RequestParam("page") Integer page, @RequestParam("size") Integer size){
        JSONArray jsonArray = new JSONArray();
        R r = new R();
//        Integer all = diaryService.list().size();
        Integer all = diaryService.getVideoDiarys();
        Integer index = (page - 1) * size;
        List<Diary> diaries = diaryService.getVideoDiaryList(index,size);
        System.out.println(diaries);
        for(Diary diary : diaries){
            JSONObject jsonObject = new JSONObject();
            System.out.println("发布时间-->"+diary.getCreateTime());
            //找照片
            Video video = videoService.getVideosBydiaryId(diary.getDiaryId());
            if(video!=null) {
                jsonObject.put("diary", diary);
                jsonObject.put("video", video.getVideoPath());
                jsonArray.add(jsonObject);
            }
        }
        System.out.println(jsonArray);
        r.setCode(200);
        r.setData(jsonArray);
        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("total",all);
        r.setMap(map);
        return r;
    }

    /**
     * 获取（待审核的非视频类的）动态列表   【注意，后面，记得把获取数量的sql的status改为2，2才是待审核状态】
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/getNotVideoDiaryList")
    public R getNotVideoDiaryList(@RequestParam("page") Integer page, @RequestParam("size") Integer size){
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
//        Integer all = diaryService.list().size();
        Integer all = diaryService.getNotVideoDiarys();
        Integer index = (page - 1) * size;
        List<Diary> diaries = diaryService.getNotVideoDiaryList(index,size);
        for(Diary diary : diaries){
            JSONObject json = new JSONObject();
            System.out.println("发布时间-->"+diary.getCreateTime());
            //找照片
            List<Picture> list = pictureService.getPicturesByDiaryId(diary.getDiaryId());
            List<String> picPath = new ArrayList<String>();
            for(Picture picture : list){
                picPath.add(picture.getPicPath());
            }
            json.put("diary",diary);
            json.put("picture",picPath);
            jsonArray.add(json);
        }
        R r = new R();
        r.setCode(200);
        r.setData(jsonArray);
        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("total",all);
        r.setMap(map);
        System.out.println(r);
        return r;
    }

    /**
     * 获取动态管理的结果返回
     */
    @PutMapping("/getAuditDiaryResult")
    public R getAuditDiaryResult(@RequestParam("diaryId") String diaryId,@RequestParam("isDeleted")Integer isDeleted,
                                 @RequestParam("ps")String ps){
        R r = new R();
        diaryService.updateAuditDiaryResult(diaryId,isDeleted,ps);
        r.setCode(200);
        if(isDeleted == 1)
            r.setMsg("删除完毕");
        if(isDeleted == 0)
            r.setMsg("取消删除完毕");
        rabbitTemplate.convertAndSend(MqConstants.DIARY_EXCAHGNE,MqConstants.DIARY_INSERT_KEY, diaryId);
        return r;
    }

    /**
     * 获取资料审核的所有信息
     *      基本信息 详细信息 择偶要求
     *      个人相册  个人mv
     */
    @GetMapping("/getInfo")
    public R getInfo(@RequestParam("page")Integer page,@RequestParam("size")Integer size){
        R r = new R();



        return r;
    }

    @GetMapping("/getReports")
    public R getReports(@RequestParam("page")Integer page,@RequestParam("size")Integer size){
        R r = new R();
        Integer total = reportService.getReportsAmount();
        Integer index = (page-1)*size;
        List<Report> reports = reportService.getReports(index,size);
        r.setData(200);
        r.setData(reports);
        Map<String,Integer> map = new HashMap();
        map.put("total",total);
        r.setMap(map);
        return r;
    }

    @PutMapping("/reportResult")
    public R reportResult(@RequestParam("id")Integer id,@RequestParam("status")Integer status,@RequestParam("ps")String ps){
        R r = new R();
        diaryService.reportResult(id,status, ps);
        r.setCode(200);
        r.setMsg("审核完毕");
        return r;
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
