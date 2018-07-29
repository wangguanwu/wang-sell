package com.weixin.sell.wangsell.service.impl;

import com.weixin.sell.wangsell.dataobject.ProductCategory;
import com.weixin.sell.wangsell.dataobject.ProductInfo;
import com.weixin.sell.wangsell.service.CategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {
    @Autowired
    private CategoryService categoryService;

    @Test
    public void findOne() {
        ProductCategory productCategory = categoryService.findOne(1);
        Assert.assertNotEquals(null,productCategory);
    }

    @Test
    public void findAll() {
        List<ProductCategory> productCategories = categoryService.findAll();
        System.out.println(productCategories);
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> productCategories = categoryService
                .findByCategoryTypeIn(Arrays.asList(1,2,3,4));
        System.out.println(productCategories);

    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryType(6);
        productCategory.setCategoryName("承认用极品");
        ProductCategory re = categoryService.save(productCategory);
        Assert.assertNotEquals(null, re);
    }

}