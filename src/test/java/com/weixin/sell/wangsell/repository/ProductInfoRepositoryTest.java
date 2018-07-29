package com.weixin.sell.wangsell.repository;

import com.weixin.sell.wangsell.dataobject.ProductInfo;
import com.weixin.sell.wangsell.enums.ProductStatusEnum;
import org.junit.Assert;
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
public class ProductInfoRepositoryTest {
    @Autowired
    private ProductInfoRepository infoRepository;
    @Test
    public void saveTest() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123456");
        productInfo.setProductName("皮蛋粥");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很好的粥");
        productInfo.setProductIcon("http://xxx.com.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(2);
        ProductInfo r = infoRepository.save(productInfo);
        Assert.assertNotNull(r);
    }
    @Test
    public void findByProductionStatus()throws Exception{
        List<ProductInfo> productInfos = infoRepository.findByProductStatus(0);
        Assert.assertNotEquals(0,productInfos.size());
    }

}