package com.weixin.sell.wangsell.config;/*
 * @author monster
 * @date 2018/7/27 11:12
 */

import com.weixin.sell.wangsell.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
public class CustomWebMvcConfiguration implements WebMvcConfigurer {
    @Autowired
    LoginInterceptor loginInterceptor ;
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//       // registry.addResourceHandler("/mystatic/**").addResourceLocations("classpath:/mystatic");
//     registry.addResourceHandler("/mystatic/**").addResourceLocations("classpath:mystatic/");
//     registry.addResourceHandler("/mystatic1/**").addResourceLocations("classpath:css/");
//    }
    //配置登录拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/admin/login");
     //   registry.addInterceptor(new Inte)

    }
}
