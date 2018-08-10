package com.weixin.sell.wangsell.utils;/*
 * @author monster
 * @date 2018/8/9 20:11
 */


import com.weixin.sell.wangsell.constant.RedisConstant;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.CookieStore;
import java.util.Arrays;
import java.util.function.Predicate;

public class LoginUtil {
    public static boolean isLogined(HttpServletRequest request,StringRedisTemplate redis ){
        Cookie[] cookies = request.getCookies();
        return Arrays.stream(cookies).anyMatch(new Predicate<Cookie>() {
            @Override
            public boolean test(Cookie cookie) {
                String s =redis.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));
                if(s!=null){
                    return true ;
                }
                return false ;
            }
        });

    }
}
