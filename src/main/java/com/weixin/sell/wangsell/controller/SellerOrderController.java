package com.weixin.sell.wangsell.controller;/*
 * @author monster
 * @date 2018/7/30 14:37
 */

import com.weixin.sell.wangsell.controller.dto.OrderDTO;
import com.weixin.sell.wangsell.dataobject.OrderDetail;
import com.weixin.sell.wangsell.sell.SellException;
import com.weixin.sell.wangsell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//卖家端管理订单
@RestController
@Slf4j
@RequestMapping("/admin/seller/order")
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
    @PostMapping("/cancel")
    public String cancelOrder(@RequestParam("orderId") String orderId,
                              Model model){
        try{
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        }catch (SellException e){
            log.error("【卖家取消订单】发生错误{}",e);
            model.addAttribute("msg",e.getMessage());
            model.addAttribute("url","/sell/admin/seller/order/list");
            return "/common/error";
        }
        return "common/success";
    }
    @GetMapping("/orderDetail")
    List<OrderDetail> detail(@RequestParam("orderId")String orderId,Model model){
        //todo
        return null;
    }
    @PostMapping
    String finishOrder(@RequestParam("orderId")String orderId,
                                    Model model){
        //todo
        return null ;
    }
}
