package com.weixin.sell.wangsell.converter;/*
 * @author wang
 * @date 2018/7/25 14:54
 */

import com.weixin.sell.wangsell.controller.dto.OrderDTO;
import com.weixin.sell.wangsell.dataobject.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMaster2OrderDTO {
    public static OrderDTO convert(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }
    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList){
        return orderMasterList.stream().map(
                e->convert(e)
        ).collect(Collectors.toList());

    }
}
