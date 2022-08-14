package com.life.admin.controller;

import com.life.admin.pojo.LoginILogs;
import com.life.admin.service.IUserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 日志管理
 */
//@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/logs")
public class logsController {
    @Autowired
    private IUserService userService;

    @GetMapping("/getPages")
    public Integer getPages(@RequestParam("size") Integer size){
        Integer pages = ( userService.getPages() + size -1 ) / size;
        System.out.println("共"+pages+"页");
        return pages;
    }


    /**
     * 查询用户登录信息 （根据登录时间进行降序查询）
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/getLoginLogs")
    public JSONObject getLoginLogs(@RequestParam("page") Integer page, @RequestParam("size")Integer size){
        Integer total = userService.getPages();
        System.out.println("共"+total+"页");
        Integer index = (page - 1)*size;
        List<LoginILogs> list = userService.getLoginLogs(index,size);
        JSONObject jsonpObject = new JSONObject();
        jsonpObject.put("total",total);
        jsonpObject.put("logs",list);
        return jsonpObject;
    }


}
