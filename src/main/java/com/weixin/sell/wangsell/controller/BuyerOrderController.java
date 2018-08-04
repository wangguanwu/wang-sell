package com.weixin.sell.wangsell.controller;/*
 * @author wang
 * @date 2018/7/25 8:47
 */

import com.weixin.sell.wangsell.controller.dto.CartDTO;
import com.weixin.sell.wangsell.controller.dto.OrderDTO;
import com.weixin.sell.wangsell.converter.OrderForm2OrderDTOConverter;
import com.weixin.sell.wangsell.dataobject.OrderDetail;
import com.weixin.sell.wangsell.dataobject.ProductInfo;
import com.weixin.sell.wangsell.enums.ResultEnum;
import com.weixin.sell.wangsell.form.OrderForm;
import com.weixin.sell.wangsell.sell.SellException;
import com.weixin.sell.wangsell.service.BuyerService;
import com.weixin.sell.wangsell.service.OrderService;
import com.weixin.sell.wangsell.service.ProductService;
import com.weixin.sell.wangsell.utils.ResultVoUtil;
import com.weixin.sell.wangsell.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
    @Autowired
    private ProductService productService ;
    @Autowired
    private OrderService orderService;
    @Autowired
    private BuyerService buyerService;
    @ResponseBody
    @PostMapping("/create")
    public ResultVo<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确，orderForm={}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.converter(orderForm);
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO createResult = orderService.create(orderDTO);
        Map<String,String> map = new HashMap<>();
        map.put("orderId",createResult.getOrderId());
        return ResultVoUtil.success(map);

    }
    @ResponseBody
    @GetMapping("/list")
    public ResultVo<List<OrderDTO>>list (@RequestParam("openid")String openid,@RequestParam("page") Integer page,@RequestParam("size") Integer size){
        if(StringUtils.isEmpty(openid)){
            log.error("【查询订单列表】， openId为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest request = PageRequest.of(page,size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid,request);
        return ResultVoUtil.success(orderDTOPage.getContent());
    }
    @ResponseBody
    @GetMapping("/detail")
    public ResultVo<OrderDTO> details(@RequestParam("openid")String openid,@RequestParam("orderId")String orderId){

        OrderDTO orderDTO =buyerService.cancelOrder(openid,orderId);
        return ResultVoUtil.success(orderDTO);
    }
    @ResponseBody
    @PostMapping("/cancel")
    public ResultVo cancel(@RequestParam("openid")String openid, @RequestParam("orderId")String orderId){
        //todo not safety
       OrderDTO orderDTO= buyerService.cancelOrder(openid,orderId);
       orderService.cancel(orderDTO);
       return ResultVoUtil.success();

    }
    @RequestMapping("/createOrder/directBuy")
    public String createOrder(CartDTO cartDTO, Model model, Integer quantity){
        if(cartDTO == null ||cartDTO.getProductId() == null || cartDTO.getProductQuantity() ==null){
            return "common/error";
        }
        ProductInfo productInfo = productService.findOne(cartDTO.getProductId());
        if(productInfo == null ){
            model.addAttribute("errorMsg","查询的产品不存在");
            return "common/error";
        }
        model.addAttribute("cartDTO",cartDTO);
        model.addAttribute("productInfo",productInfo);
        return "orderPage";
    }

}
