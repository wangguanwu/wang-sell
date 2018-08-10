package com.weixin.sell.wangsell.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/*
 * @author monster
 * @date 2018/8/9 15:46
 */
@Aspect
@Component
public class SellerAuthorizeAspect {
    @Pointcut("execution(public * com.weixin.sell.wangsell.controller.Seller*.*(..))"+
    "&&!execution(public * com.weixin.sell.wangsell.controller.SellerController.*(..))")
    public void verify(){}
    @Before("verify()")
    public void doVerify(){
        ServletRequestAttributes attributes =(ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //查询cookie

    }
}
