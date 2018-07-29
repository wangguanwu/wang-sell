package com.weixin.sell.wangsell.service.impl;

import com.weixin.sell.wangsell.controller.dto.OrderDTO;
import com.weixin.sell.wangsell.dataobject.OrderDetail;
import com.weixin.sell.wangsell.dataobject.OrderMaster;
import com.weixin.sell.wangsell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {
    private final static String OPENID = "11112";
    @Autowired
    private OrderService orderService;
    @Test
    public void create() {
        OrderDTO orderDTO  = new OrderDTO();
        orderDTO.setBuyerName("王观武");
        orderDTO.setBuyerAddress("深圳");
        orderDTO.setBuyerPhone("11111111");
        orderDTO.setBuyerOpenid(OPENID);
        List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("123456");
        o1.setProductQuantity(1);
        OrderDetail o2 = new OrderDetail();
        o2.setProductId("123458");
        o2.setProductQuantity(2);


        orderDetailList.add(o1);
        orderDetailList.add(o2);
        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO result=orderService.create(orderDTO);
        log.info("[创建订单] result={}",result);

    }

    @Test
    public void findOne() {
        OrderDTO orderDTO =orderService.findOne("1532500465812338839");
        log.info("查询单个订单:result={}",orderDTO);
        System.out.println(orderDTO);

    }

    @Test
    public void findList() {
        Page<OrderDTO> orderMasterPage = orderService.findList(OPENID,new PageRequest(0,10));
        System.out.println(orderMasterPage.getContent());
    }

    @Test
    public void cancel() {

        OrderDTO orderDTO =orderService.findOne("1532500465812338839");
        OrderDTO result = orderService.cancel(orderDTO);
        System.out.println(result);


    }

    @Test
    public void finish() {
        OrderDTO orderDTO =orderService.findOne("1532500465812338839");
        OrderDTO result =orderService.finish(orderDTO);
        System.out.println(result);

    }

    @Test
    public void paid() {
        OrderDTO orderDTO =orderService.findOne("1532500465812338839");
        OrderDTO result =orderService.paid(orderDTO);
        System.out.println(result);
    }

    @Test
    public void findList1() {
    }
}