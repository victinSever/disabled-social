//package com.admin.common;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.concurrent.TimeUnit;
//
//@Configuration
//public class RequestInterceptor implements WebMvcConfigurer {
//
//    @Autowired
//    private StringRedisTemplate redisTemplate;
//
//    public void addInterceptors(InterceptorRegistry registry){
//        registry.addInterceptor(new HandlerInterceptor() {
//            @Override
//            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//                String token = request.getHeader("token");
//
////                                      从redis中获取token
//                if(token != null && redisTemplate.opsForValue().get(token) != null ){
//                    //每次认证后就重置30分钟
//                    redisTemplate.expire(token,30, TimeUnit.MINUTES);
//                    return true;  //获取到就返回true;
//                }
//
//                //设置响应401
//                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//
//                return false;
//            }
//        }).excludePathPatterns("/admin/login","/admin/register");
//    }
//}
