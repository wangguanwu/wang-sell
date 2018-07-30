package com.weixin.sell.wangsell.controller;/*
 * @author monster
 * @date 2018/7/27 9:47
 * 处理默认登陆页面的请求
 */

import com.weixin.sell.wangsell.vo.ProductVo;
import com.weixin.sell.wangsell.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private BuyerProductionController productionController;
    /*
     *默认主页将会查询所有的商品列表
     * @author wang
     * @date 2018/7/30 15:51
     * @parm
     * @return
     */
    @RequestMapping("/")
    public String index(Model model ){
        ResultVo<List<ProductVo>> resultVo = productionController.list();
        List<ProductVo> productVolist =  resultVo.getData() ;
        model.addAttribute("productVoList",productVolist);
        return "home";
    }

}
