package com.weixin.sell.wangsell.repository;

import com.weixin.sell.wangsell.dataobject.OrderMaster;
import org.hibernate.criterion.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    private final static String OPENID = "110";
    @Test
    public  void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123456");
        orderMaster.setBuyerName("师兄");
        orderMaster.setBuyerAddress("北京");
        orderMaster.setBuyerOpenid("110");
        orderMaster.setBuyerPhone("11111111");
        orderMaster.setOrderAmount(new BigDecimal(2.3));
        orderMasterRepository.save(orderMaster);
    }
    @Test
    public void findByBuyerOpenId() {
        PageRequest request = new PageRequest(0,3);
        Page<OrderMaster> result = orderMasterRepository.findByBuyerOpenid(OPENID,request);
        System.out.println(result.getTotalElements());
    }
}