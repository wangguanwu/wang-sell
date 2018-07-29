package com.weixin.sell.wangsell.controller;/*
 * @author monster
 * @date 2018/7/27 9:47
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    @RequestMapping("/")
    public String login(){
        return "login";
    }

}
