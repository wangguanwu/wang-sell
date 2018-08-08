package com.weixin.sell.wangsell.service.impl;

import com.weixin.sell.wangsell.dataobject.ProductInfo;
import com.weixin.sell.wangsell.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {
    @Autowired
    private ProductService productService;
    @Test
    public void findOne() {
        ProductInfo productInfo = productService.findOne("123456");
        Assert.assertEquals("123456",productInfo.getProductId());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo>  infos = productService.findUpAll();
        Assert.assertNotNull(infos);
    }

    @Test
    public void findAll() {
        PageRequest pr = new PageRequest(0,2);
        Page<ProductInfo> productInfos = productService.findAll(pr);
        System.out.println(productInfos.getContent());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123457");
        productInfo.setProductName("皮蛋粥");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很好的虾");
        productInfo.setProductIcon("http://xxx.com.jpg");
        productInfo.setProductStatus(1);
        productInfo.setCategoryType(2);
        ProductInfo r = productService.save(productInfo);
        Assert.assertNotNull(r);
    }
    @Test
    public void getByCategoryType(){
        List<ProductInfo> productInfoList =productService.findByProdcutCategory(2);
        System.out.println(productInfoList);
    }
    @Test
    public void onSale(){
       ProductInfo re = productService.onSale("123456");
       Assert.assertNotNull(re);

    }
    @Test
    public void downSale(){
        ProductInfo re = productService.offSale("123456");
        Assert.assertNotNull(re);
    }
}