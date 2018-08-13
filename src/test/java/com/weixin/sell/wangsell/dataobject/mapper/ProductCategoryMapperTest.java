package com.weixin.sell.wangsell.dataobject.mapper;

import com.weixin.sell.wangsell.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryMapperTest {
    @Autowired
    private ProductCategoryMapper productCategoryMapper;
    @Test
    public void insertByMap() {
        Map<String,Object> map = new HashMap<>();
        map.put("category_type",11);
        map.put("category_name","关务最爱");
        int result = productCategoryMapper.insertByMap(map);
        Assert.assertEquals(1,result);
    }
    @Test
    public void testFindByCategoryType(){
        ProductCategory productCategory =productCategoryMapper.findByCategoryType(11);
        Assert.assertNotNull(productCategory);
    }
    @Test
   public void  updateByCategoryType(){
        int result =productCategoryMapper.updateByCategoryType(12,"关务最不爱");
        Assert.assertEquals(1,result);
   }

    @Test
    public void deleteByType() {
        int result = productCategoryMapper.deleteByType(12);
        Assert.assertEquals(1,result);

    }
    @Test
    public void selectByCategoryType(){
        ProductCategory productCategory = productCategoryMapper.ss(11);
        Assert.assertNotNull(productCategory);
    }
}