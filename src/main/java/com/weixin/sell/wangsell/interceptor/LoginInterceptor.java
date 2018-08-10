package com.weixin.sell.wangsell.interceptor;/*
 * @author monster
 * @date 2018/7/29 21:47
 */

import com.weixin.sell.wangsell.constant.CookieConstant;
import com.weixin.sell.wangsell.constant.RedisConstant;
import com.weixin.sell.wangsell.dataobject.SellerInfo;
import com.weixin.sell.wangsell.service.SellerService;
import com.weixin.sell.wangsell.utils.CookieUtil;
import com.weixin.sell.wangsell.utils.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    SellerService sellerService ;
    @Autowired
    StringRedisTemplate template;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
      return LoginUtil.isLogined(request,template);
    }

}
