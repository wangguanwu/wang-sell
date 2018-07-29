package com.weixin.sell.wangsell.service;

import com.weixin.sell.wangsell.dataobject.ProductCategory;

import java.util.List;

public interface CategoryService {
    ProductCategory findOne(Integer categoryId);
    List<ProductCategory> findAll();
    List<ProductCategory> findByCategoryTypeIn(List<Integer> list);
    ProductCategory save(ProductCategory productCategory);

}
