//package com.admin.common;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTCreator;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.interfaces.DecodedJWT;
//
//import java.util.Calendar;
//import java.util.Map;
//
//
//public class JWTUtil {
//
//    /**
//     * @Description: 加密签名，最重要，完全保密
//     * @param null
//     * @return null
//     **/
//    private static final String SING = "JZW";
//
//
//    /**
//     * @Description: 对数据进行JWT加密
//     * @param map
//     * @return String
//     **/
//    public static String getToken(Map<String ,String > map ){
//        Calendar instance = Calendar.getInstance();
//        //默认15天过期
//        instance.add(Calendar.HOUR,24 * 15);
//
//        //创建JWT.builder
//        JWTCreator.Builder builder = JWT.create();
//      /*  builder.withIssuedAt()//发行时间
//                .withExpiresAt()//有效时间*/
//        map.forEach((k,v)->{
//            builder.withClaim(k,v);
//        });
//
//
//        //sing
//        String token = builder.withExpiresAt(instance.getTime())
//                .sign(Algorithm.HMAC256(SING));//加密
//
//        return token;
//    }
//
//    /**
//     * @Description: 根据token得到响应的数据
//     * @param token
//     * @return DecodedJWT
//     **/
//    public static DecodedJWT verifyUser(String token){
//        return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
//    }
//
//
//}
