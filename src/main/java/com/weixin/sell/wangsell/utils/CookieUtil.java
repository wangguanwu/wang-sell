package com.weixin.sell.wangsell.utils;/*
 * @author monster
 * @date 2018/8/9 18:32
 */

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
    public static void set(HttpServletResponse response,String name,String value,int maxage){
        Cookie  cookie = new Cookie(name,value);
        cookie.setMaxAge(maxage);
        response.addCookie(cookie);
    }
    public static Cookie get(){
        return null ;
    }
}
