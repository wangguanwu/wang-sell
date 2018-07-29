package com.weixin.sell.wangsell.service;/*
 * @author monster
 * @date 2018/7/26 9:57
 */

import com.weixin.sell.wangsell.controller.dto.OrderDTO;

public interface BuyerService {
    OrderDTO findOrderOne(String openid, String orderId);
    OrderDTO cancelOrder(String openid,String orderId);

}
