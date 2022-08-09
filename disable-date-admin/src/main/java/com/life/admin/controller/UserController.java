package com.life.admin.controller;

import com.life.admin.pojo.User;
import com.life.admin.service.IUserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户管理
 *      用户信息
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;


    @GetMapping("/getPages")
    public Integer getPages(@RequestParam("size") Integer size){
        Integer pages = ( userService.list().size() + size -1 ) / size;
        return pages;
    }

    /**
     * //获取用户信息列表
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/getUserList")
    public JSONObject getUserList(@RequestParam("page") Integer page, @RequestParam("size") Integer size){
        Integer total = userService.list().size();
        Integer index =( page - 1 ) * size;
        List<User> list = userService.getUserList(index,size);
        System.out.println(list);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total",total);
        jsonObject.put("list",list);
        System.out.println(jsonObject);
        return jsonObject;
    }

    /**
     * （批量）删除用户
     * @param
     * @return
     */
    @ResponseBody
    @DeleteMapping("/deleteUser")
    public boolean deleteUserById(@RequestParam("userId") Integer id){
//        if(idList.length != 0) {
//            for (Integer id : idList) {
//                userService.removeById(id);
//            }
//            return true;
//        }else return false;
        System.out.println("-------来删除了");
        userService.deleteUserByUserId(id);
        return true;
    }


}
