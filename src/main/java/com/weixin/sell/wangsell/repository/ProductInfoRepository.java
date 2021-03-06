package com.weixin.sell.wangsell.repository;

import com.weixin.sell.wangsell.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {
    List<ProductInfo> findByProductStatus(Integer productStatus);
    List<ProductInfo> findByCategoryType (Integer categoryType);
    List<ProductInfo> findByProductId(List<String> productIdList);

}
