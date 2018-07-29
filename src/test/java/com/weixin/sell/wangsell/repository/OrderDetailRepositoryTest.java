package com.weixin.sell.wangsell.repository;

import com.weixin.sell.wangsell.dataobject.OrderDetail;
import org.apache.catalina.LifecycleState;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("12345678909");
        orderDetail.setOrderId("1111112");
        orderDetail.setProductId("111112");
        orderDetail.setProductIcon("http://xxxx.jpg");
        orderDetail.setProductName("皮蛋瘦肉粥");
        orderDetail.setProductPrice(new BigDecimal(3.4));
        orderDetail.setProductQuantity(2);
        OrderDetail orderDetail1=orderDetailRepository.save(orderDetail);
        System.out.println(orderDetail1);
    }
    @Test
    public void findByOrderId() {
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId("1111112");
        System.out.println(orderDetailList);
    }
}