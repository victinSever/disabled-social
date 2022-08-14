//package com.admin.common;
//
//import com.admin.pojo.User;
//import com.alibaba.fastjson.JSONObject;
//import com.fasterxml.jackson.databind.util.BeanUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//public class TOKEN {
//    @Autowired
//    private StringRedisTemplate redisTemplate;
//
//    @PostMapping("/login")
//    public void login(@RequestParam("loginName") String loginName, @RequestParam("password")String password) throws Exception{
//        User user = userService.login(loginName, password);
//        System.out.println(user);
//        if (user != null && user.getIsDeleted() == 1){
//            Map<String,String> map = new HashMap<String, String>();
//            map.put("userId",user.getUserId().toString());
//            map.put("loginName",user.getLoginName());
//            map.put("passwordMd5",user.getPasswordMd5());
//            //生成令牌
//            String token = JWTUtil.getToken(map);
//            System.out.println("token:" + token);
//            String user_str = JsonUtil.objToJson(user);
//            //token 与user 绑定存入redis
//            redisTemplate.opsForValue().set(token,user_str);
//            UserVO userVO = new UserVO();
//            BeanUtil.copyProperties(user,userVO);
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("token",token);
//            jsonObject.put("user",userVO);
////            return ApiResultHandler.buildApiResult(200,"登录成功",jsonObject);
//        }else if (user.getIsDeleted() == 2) {
////            return ApiResultHandler.buildApiResult(401,"账号已注册，正在等待审核",null);
//        } else {
////            return ApiResultHandler.buildApiResult(404,"账号或密码错误",null);
//        }
//    }
//
//    @PostMapping("/updateNickName")
//    public ApiResult updateNickName(@RequestParam("loginName") String loginName,
//                                    @RequestParam("nickName") String nickName, HttpServletRequest request)
//            throws IOException {
//        //令牌检验
//        String token = request.getHeader("token");
//        User userFromRedis = userUtil.getUserFromRedis(token);
//        if (userFromRedis.getLoginName().equals(loginName)){
//            if(userService.updateNickName(loginName,nickName).equals(ServiceResultEnum.SUCCESS.getResult())){
//                return ApiResultHandler.buildApiResult(200,"修改成功",ServiceResultEnum.SUCCESS.getResult());
//            }
//        }
//        return ApiResultHandler.buildApiResult(404,"修改失败",ServiceResultEnum.ERROR.getResult());
//
//    }
//
//}
