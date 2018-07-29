package com.weixin.sell.wangsell.repository;

import com.weixin.sell.wangsell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository  extends JpaRepository<ProductCategory,Integer> {
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> list);
}
