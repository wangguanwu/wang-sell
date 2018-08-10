package com.weixin.sell.wangsell.controller;/*
 * @author monster
 * @date 2018/7/30 10:50
 */

import com.weixin.sell.wangsell.config.UrlConfig;
import com.weixin.sell.wangsell.constant.CookieConstant;
import com.weixin.sell.wangsell.constant.RedisConstant;
import com.weixin.sell.wangsell.dataobject.SellerInfo;
import com.weixin.sell.wangsell.enums.ResultEnum;
import com.weixin.sell.wangsell.sell.SellException;
import com.weixin.sell.wangsell.service.SellerService;
import com.weixin.sell.wangsell.utils.CookieUtil;
import com.weixin.sell.wangsell.utils.LoginUtil;
import com.weixin.sell.wangsell.utils.ResultVoUtil;
import com.weixin.sell.wangsell.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@Slf4j
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    private SellerService sellerService ;
    @Autowired
    StringRedisTemplate redis;
    @Autowired
    private UrlConfig urlConfig ;
    @GetMapping("/login")
    public String fillForm(HttpServletRequest request ){
        if(LoginUtil.isLogined(request,redis)){
            return "redirect:"+urlConfig.sell+"/sell/seller/order/list";
        }
        return "login";
    }
    @PostMapping("/validate")
    public String login(@RequestParam(value = "openid",required = false)String openid,
                        SellerInfo sellerInfo, Model model,
                        HttpServletResponse response){
        //1.查询是否存在该用户
        SellerInfo result = sellerService.findByUsernameAndPassword(sellerInfo);
        if(result==null){
            model.addAttribute("errorMsg",ResultEnum.LOGIN_FAIL.getMsg());
            model.addAttribute("url","sell/seller/order/list");
            return "/common/error";
        }

        //2.redis 保存cookie
        String token = UUID.randomUUID().toString();
        Integer expires = RedisConstant.EXPIRE;
        redis.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX,token),"name",expires,TimeUnit.SECONDS);
        //3.设置token到cookie
        Cookie cookie = new Cookie("token",token);
        CookieUtil.set(response,CookieConstant.TOKEN,token,expires);
        return "redirect:"+urlConfig.sell+"/sell/seller/order/list";
    }
    @GetMapping("/")
    public String defaultPage(){
        return "redirect:/seller/order/list";
    }

    @PostMapping("/modify")
    public ResultVo updateSellerInfo(SellerInfo sellerInfo){
        if(StringUtils.isEmpty(sellerInfo.getUsername())&&StringUtils.isEmpty(sellerInfo.getPassword())){
            log.error("【修改管理员信息】参数不合法{}",sellerInfo);
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        int result = sellerService.update(sellerInfo);
       if(result != 1){
           log.error("【修改管理员】修改失败");
           return ResultVoUtil.error(ResultEnum.SELLERINFO_UPDATE_ERROR.getCode(),"修改失败");
       }
       ResultVo resultVo = ResultVoUtil.success();
       return resultVo ;
    }
    @PostMapping("/add")
    public ResultVo<SellerInfo> addSellerInfo(SellerInfo sellerInfo){
        if(StringUtils.isEmpty(sellerInfo.getUsername())||
                StringUtils.isEmpty(sellerInfo.getPassword())
                ||StringUtils.isEmpty(sellerInfo.getOpenid())){
            log.info("【新增管理员】参数不合法{}",sellerInfo);
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        SellerInfo result = sellerService.save(sellerInfo);
        if(result == null ){
            log.error("【新增管理员】失败");
            return ResultVoUtil.error(ResultEnum.SELLER_ADD_ERROR.getCode(),"添加失败");
        }
        return ResultVoUtil.success(result);
    }
}
