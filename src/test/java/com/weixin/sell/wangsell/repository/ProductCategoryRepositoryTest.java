package com.weixin.sell.wangsell.repository;

import com.weixin.sell.wangsell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Test
    public void findOneTest(){
        ProductCategory productCategory =productCategoryRepository.findById(1).get();
        System.out.println(productCategory);
    }
    @Test
    public void saveTest(){
        ProductCategory productCategory =productCategoryRepository.getOne(5);
        productCategory.setCategoryType(5);
        productCategory.setCategoryName("男生最爱的");
        productCategory.setCategoryType(4);
        productCategoryRepository.save(productCategory);
    }
    @Test
    public void findByCategoryTest(){
        List<Integer> list = Arrays.asList(2,3,4);
        List<ProductCategory> categoryList = productCategoryRepository.findByCategoryTypeIn(list);
        System.out.println(categoryList);
    }
}
