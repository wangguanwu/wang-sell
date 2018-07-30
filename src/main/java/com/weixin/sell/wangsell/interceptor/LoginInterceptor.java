package com.weixin.sell.wangsell.interceptor;/*
 * @author monster
 * @date 2018/7/29 21:47
 */

import com.weixin.sell.wangsell.dataobject.SellerInfo;
import com.weixin.sell.wangsell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    SellerService sellerService ;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String username = request.getParameter("username");
        String  password = request.getParameter("password");
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setUsername(username);
        sellerInfo.setPassword(password);
        List<SellerInfo> result = sellerService.findByName(sellerInfo.getUsername());
        return result.stream().anyMatch(sellerInfo::isPasswordEqual);
    }

}
