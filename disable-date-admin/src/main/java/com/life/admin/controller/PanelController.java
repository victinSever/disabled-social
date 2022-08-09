package com.life.admin.controller;

import com.life.admin.pojo.Administrator;
import com.life.admin.pojo.User;
import com.life.admin.service.AdministratorService;
import com.life.admin.service.IUserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.life.admin.pojo.Version;
import com.life.admin.service.IVersionService;

import java.util.List;

/**
 * 控制面板模块
 *      工作台
 *      分析页
 *      监控
 */
@RestController
@RequestMapping("/panel")
public class PanelController {

    @Autowired
    private IVersionService versionService;

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private IUserService userService;


    /**
     * 获取版本更新的信息（根据发行时间 进行 降序）
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/getVersions")
    public JSONObject getVersion(@RequestParam("page") Integer page, @RequestParam("size") Integer size){
        Integer start = (page - 1) * size;
        Integer pages = (versionService.list().size() + size - 1) / size;  //总页数
        List<Version> list = versionService.getVersion(start,size);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pages",pages);
        jsonObject.put("version",list);
        System.out.println(jsonObject);
        return jsonObject;
    }

    /**
     * 获取所有的管理员的信息
     * @return
     */
    @GetMapping("/getAllAdministrator")
    public List<Administrator> getAllAdministrator(){
        List<Administrator> list = administratorService.list();
        System.out.println(list);
        return list;
    }

    /**
     * 获取年龄范围的人数
     * @return
     */
    @GetMapping("/getAmountOfAgeRange")
//    public JSONObject getAmountOfAgeRange(@Param("ageRange") String[] ageRange){
    public JSONObject getAmountOfAgeRange(){
        String[] ageRange = new String[6];
        ageRange[0] = "1 17";
        ageRange[1] = "18 25";
        ageRange[2] = "26 35";
        ageRange[3] = "36 45";
        ageRange[4] = "46 60";
        ageRange[5] = "61 150";
        JSONObject jsonObject = new JSONObject();
        int n = 0;
        for(String range : ageRange){
            jsonObject.put(range,n);
        }
        List<User> list = userService.list();
        for(User user : list){
            for(String age : ageRange){
                int n1 = Integer.parseInt(age.split(" ")[0]);
                int n2 = Integer.parseInt(age.split(" ")[1]);
                if(user.getAge() >= n1 && user.getAge() <= n2){
                    Integer value = (Integer) jsonObject.get(age);
                    jsonObject.put(age,value+1);
                    break;
                }
            }
        }
        for(String age : ageRange){
            System.out.println(age+" : "+ jsonObject.get(age) + "人");
        }
        return jsonObject;
    }




}
