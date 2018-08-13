package com.weixin.sell.wangsell.handler;

import com.weixin.sell.wangsell.config.UrlConfig;
import com.weixin.sell.wangsell.sell.SellException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
 * @author monster
 * @date 2018/8/13 8:38
 */
@ControllerAdvice
public class SellExceptionHandler {
    @Autowired
    public UrlConfig urlConfig;
    @ExceptionHandler(value=SellException.class)
    public String handlerSellerException(SellException e, Model model){
        model.addAttribute("errorMsg",e.getMessage());
        model.addAttribute("url",urlConfig+"/sell");
        return "common/error";
    }

}
