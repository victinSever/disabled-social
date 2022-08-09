package com.life.admin.controller;

import com.life.admin.common.Md5Util;
import com.life.admin.common.R;
import com.life.admin.pojo.Administrator;
import com.life.admin.pojo.Menuchlidren;
import com.life.admin.pojo.Menumoudle;
import com.life.admin.service.AdministratorService;
import com.life.admin.service.IMenuService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 系统管理
 *      管理员管理
 *      菜单管理
 */
@RestController
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private Md5Util md5Util;

//    @Autowired
////    private StringRedisTemplate redisTemplate;

    /**
     * 管理员注册
     * @param account
     * @param userName
     * @param password
     * @param department
     * @param role
     * @return
     */
    @PostMapping("/register")
    public boolean register(@RequestParam("account") String account,
                            @RequestParam("username") String userName,@RequestParam("password")String password,
                            @RequestParam("department") String department,@RequestParam("role") String role){

        System.out.println("原密码："+password);
        password =md5Util.md5(password);
        System.out.println("加密后："+password);
        Integer id = null;
        //注册管理员
        administratorService.insertAdministrator(id,account,userName,password,department,role);
        return true;
    }

    @PostMapping("/login")
    public R login(@RequestParam("username") String userName, @RequestParam("password") String password, HttpServletRequest request){
        //得到HttpSession类型的对象
        HttpSession session = request.getSession();
        System.out.println("原密码："+password);
        password =md5Util.md5(password);
        System.out.println("加密后："+password);
        System.out.println("用户名："+userName);
        //根据用户名查找管理员
        Administrator administrator = administratorService.getAdministratorByUserName(userName);

//        String uuid = "user-token;" + UUID.randomUUID();
        //存进redis，值为用户id                                                       设置过期时间为30分钟
//        redisTemplate.opsForValue().set(uuid,administrator.getId().toString(),30, TimeUnit.MINUTES);

        R r = new R();  //存储返回的结果
        if(administrator!=null) {
            if (password.equals(administrator.getPassword())) {
                r.setCode(1);   //1：成功 0；失败 -1:没有该用户
                r.setMsg("登录成功");
                session.setAttribute("userId", UUID.randomUUID());
                Map<String,Administrator> map = new HashMap<String, Administrator>();
                map.put("admin",administrator);
                r.setMap(map);
            } else {
                r.setCode(0);
                r.setMsg("登录失败，密码错误，请重新输入");
            }
        }else {
            r.setCode(-1);
            r.setMsg("不存在该用户，请重新输入");
        }
        System.out.println(r);
        return r;
    }

    /**
     * 获取管理员的表的总页数
     * @param size
     * @return
     */
    @GetMapping("/getPages")
    public Integer getPages(@RequestParam("size") Integer size){
        Integer pages = (administratorService.list().size() + size -1)/size;
        System.out.println("共有"+pages+"页");
        return pages;
    }

    /**
     * 获取管理员列表
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/getAdministratorList")
    public List<Administrator> getAdministratorList(@RequestParam("page") Integer page, @RequestParam("size") Integer size){
        Integer start = (page - 1) * size;
        System.out.println(start +" " + page + " "+size);
        List<Administrator> list = administratorService.getAdministratorList(start,size);
        System.out.println(list);
        return list;
    }

    /**
     * 根据id（数组批量）删除管理员
     * @param idList
     * @return
     */
    @DeleteMapping("/deleteAdministrator")
    public boolean deleteAdministrator(@RequestParam("id") Integer[] idList){
        if(idList.length != 0) {
            for (Integer id : idList) {
                administratorService.removeById(id);
            }
            return true;
        }else return false;
    }

    /**
     * 重置管理员密码
     * @param id
     * @return
     */
    @PutMapping("/resetPassword")
    public R resetPassword(@RequestParam("id") Integer id){
        administratorService.resetPassword(id);
        R result = new R();
        result.setMsg("密码重置为：12345678");
        return result;
    }

    /**
     * 获取菜单的目录
     * @return
     */
    @GetMapping("/getMenuMoudleList")
    public JSONObject getMenuMoudleList(){
        //获取菜单模块
        List<Menumoudle> list = menuService.list();
        JSONObject jsonObject = new JSONObject();
        for(Menumoudle menumoudle : list){
            //通过菜单模块名字来查找它的菜单孩子
            List<Menuchlidren> menuchlidrenList = menuService.getMenuchlidren(menumoudle.getId());
            jsonObject.put(menumoudle.getName(),menuchlidrenList);
        }
        System.out.println(jsonObject);
        return jsonObject;
    }

    /**
     * 更改菜单是否展示出的状态
     * @param id
     * @param permission
     * @return
     */
    @PutMapping("/updateStatus")
    public boolean updateStatus(@RequestParam("id") Integer id,@RequestParam("permission") Integer permission){
        menuService.updateStatus(id,permission);
        return true;
    }

    /**
     * 获取管理员的个人信息
     * @param id
     * @return
     */
    @GetMapping("/getAdministrator")
    public Administrator getAdministrator(@RequestParam("id")Integer id){
        Administrator administrator = administratorService.getById(id);
        return  administrator;
    }

    /**
     * 修改管理员电话
     * @param id
     * @param phone
     * @return
     */
    @PutMapping("/changePhone")
    public boolean changePhone(@RequestParam("id") Integer id,@RequestParam("phone") String phone){
        administratorService.changePhone(id,phone);
        return true;
    }

    /**
     * 修改管理员邮箱
     * @param id
     * @param email
     * @return
     */
    @PutMapping("/changeEmail")
    public boolean changeEmail(@RequestParam("id") Integer id,@RequestParam("email") String email){
        administratorService.changeEmail(id,email);
        return true;
    }

    /**
     * 修改信息
     * @param id
     * @param email
     * @param userName
     * @param phone
     * @param introduction
     * @param tab
     * @return
     */
    @PutMapping("/changeInfo")
    public boolean changeInfo(@RequestParam("id") Integer id,@RequestParam("email") String email,
                              @RequestParam("userName") String userName,@RequestParam("phone") String phone,
                              @RequestParam("introduction") String introduction,
                              @RequestParam("tab") String tab){
        administratorService.changeInfo(id,userName,phone,email,introduction,tab);
        return true;
    }




}
