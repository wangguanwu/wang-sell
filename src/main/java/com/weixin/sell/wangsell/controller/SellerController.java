package com.weixin.sell.wangsell.controller;/*
 * @author monster
 * @date 2018/7/30 10:50
 */

import com.weixin.sell.wangsell.dataobject.SellerInfo;
import com.weixin.sell.wangsell.enums.ResultEnum;
import com.weixin.sell.wangsell.sell.SellException;
import com.weixin.sell.wangsell.service.SellerService;
import com.weixin.sell.wangsell.utils.ResultVoUtil;
import com.weixin.sell.wangsell.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@Slf4j
@RestController
@RequestMapping("/administrator/oper/")
public class SellerController {
    @Autowired
    private SellerService sellerService ;
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
