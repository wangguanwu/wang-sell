package com.weixin.sell.wangsell.controller;/*
 * @author monster
 * @date 2018/7/30 14:37
 */

import com.weixin.sell.wangsell.controller.dto.OrderDTO;
import com.weixin.sell.wangsell.dataobject.OrderDetail;
import com.weixin.sell.wangsell.enums.ResultEnum;
import com.weixin.sell.wangsell.sell.SellException;
import com.weixin.sell.wangsell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//卖家端管理订单
@Controller
@Slf4j
@RequestMapping("/seller/order")
public class SellerOrderController {
    @Autowired
    private OrderService orderService ;

    /*
     *功能描述 查询订单列表
     * @author wang
     * @date 2018/7/30 14:38
     * @param page 从第几页开始
     * @param size 每页有多少数据
     * @return 订单列表
     */

    @GetMapping("/list")
    public String getList(@RequestParam(value="page",defaultValue="1")Integer page,
                          @RequestParam(value="size",defaultValue = "10") Integer size,
                          Model model){
        PageRequest pageRequest = PageRequest.of(page-1,size);
        Page<OrderDTO> orderDTOPage =  orderService.findList(pageRequest);
     
        model.addAttribute("orderDTOPage",orderDTOPage);
        model.addAttribute("currentPage",page);
        model.addAttribute("size",size);
        return "order/list";
    }
    /*
     *功能描述 取消订单
     * @author wang
     * @date 2018/7/30 14:48
     * @param orderID
     * @return
     */
    @GetMapping("/cancel")
    public String cancelOrder(@RequestParam("orderId") String orderId,
                              Model model){
        try{
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        }catch (SellException e){
            log.error("【卖家取消订单】发生错误{}",e);
            model.addAttribute("errorMsg",e.getMessage());
            model.addAttribute("url","/sell/seller/order/list");
            return "/common/error";
        }
        model.addAttribute("msg",ResultEnum.ORDER_CANCEL_SUCCESS.getMsg());
        model.addAttribute("url", "/sell/seller/order/list");
        return "common/success";
    }

    /*
     *订单详情
     * @author wang
     * @date 2018/8/9 10:03
     * @param
     * @return
     */
    @GetMapping("/detail")
    public String detail(@RequestParam("orderId")String orderId,Model model){
        OrderDTO orderDTO = new OrderDTO();
        try{
            orderDTO = orderService.findOne(orderId);
        }catch (SellException e){
            log.error("【查询订单】出现异常{}",   e);
            model.addAttribute("errorMsg",e.getMessage());
            model.addAttribute("url","/sell/seller/order/list");
            return "common/error";
        }
        model.addAttribute("orderDTO",orderDTO);
        return "order/detail";
    }

    /*
     *完成订单
     * @author wang
     * @date 2018/8/9 10:07
     * @param
     * @return
     */
    @GetMapping("/finish")
    public String finishOrder(@RequestParam("orderId")String orderId,
                                    Model model){
        try{
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
        }catch (SellException e){
            log.error("【完成订单】出现异常{}",e);
            model.addAttribute("error",e.getMessage());
            model.addAttribute("url","/sell/seller/order/list");
            return "common/error";
        }
        model.addAttribute("msg",ResultEnum.ORDER_FINISH_SUCCESS.getMsg());
        model.addAttribute("url","/sell/seller/order/list");
        return "common/success";
    }
}
