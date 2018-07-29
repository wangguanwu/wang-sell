package com.weixin.sell.wangsell.converter;/*
 * @author wang
 * @date 2018/7/25 10:42
 */

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.weixin.sell.wangsell.controller.dto.OrderDTO;
import com.weixin.sell.wangsell.dataobject.OrderDetail;
import com.weixin.sell.wangsell.enums.ResultEnum;
import com.weixin.sell.wangsell.form.OrderForm;
import com.weixin.sell.wangsell.sell.SellException;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class OrderForm2OrderDTOConverter {
    public static OrderDTO converter(OrderForm orderForm){
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
        try {
            orderDetailList =gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
            }.getType());
        }catch (Exception e){
            log.error("【对象转换】错误，string={}",orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        orderDTO.setOrderDetailList(orderDetailList);
     //   orderDTO.setOrderDetailList();
        return orderDTO;
    }
}
