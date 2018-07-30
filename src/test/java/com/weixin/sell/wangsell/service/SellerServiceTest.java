package com.weixin.sell.wangsell.service;

import com.weixin.sell.wangsell.dataobject.SellerInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class SellerServiceTest {
    @Autowired
    private SellerService sellerService ;

    @Test
    public void findById() {
        SellerInfo sellerInfo = sellerService.findById("10001");
        System.out.println(sellerInfo);
    }

    @Test
    public void findByName() {
        List<SellerInfo> sellerInfos = sellerService.findByName("wang");
        System.out.println(sellerInfos);
    }

    @Test
    public void update() {
        SellerInfo sellerInfo = sellerService.findById("10001");
        sellerInfo.setUsername("wangguanwu");
        SellerInfo result =sellerService.save(sellerInfo);
        Assert.assertEquals("wangguanwu",result.getUsername());

    }

    @Test
    public void findByUsernameAndPassword() {
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setUsername("wangguanwu");
        sellerInfo.setPassword("wang");
        SellerInfo result = sellerService.findByUsernameAndPassword(sellerInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void save() {
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setId("11111111178");
        sellerInfo.setUsername("zhangsan");
        sellerInfo.setPassword("zhangsan");
        sellerInfo.setOpenid("11111111110");
        SellerInfo result= sellerService.save(sellerInfo);
        Assert.assertEquals("zhangsan",result.getUsername());
        System.out.println(result);
    }
}