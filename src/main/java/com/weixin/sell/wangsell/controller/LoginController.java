package com.weixin.sell.wangsell.controller;/*
 * @author monster
 * @date 2018/7/29 21:40
 */

import com.weixin.sell.wangsell.dataobject.SellerInfo;
import com.weixin.sell.wangsell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    private SellerService sellerService ;
    @PostMapping("/login")
    public String login(SellerInfo sellerInfo){
        SellerInfo admin =sellerService.findByUsernameAndPassword(sellerInfo);
        return admin == null ? "login" : "home";
    }
    @PostMapping("/logout")
    public String logout(SellerInfo sellerInfo){
        return null ;
    }

}
