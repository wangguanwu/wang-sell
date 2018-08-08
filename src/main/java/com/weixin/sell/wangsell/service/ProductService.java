package com.weixin.sell.wangsell.service;

import com.weixin.sell.wangsell.controller.dto.CartDTO;
import com.weixin.sell.wangsell.dataobject.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    ProductInfo findOne(String productId);
    List<ProductInfo> findUpAll();
    Page<ProductInfo> findAll(Pageable pageable);
    ProductInfo save(ProductInfo productInfo);
    void decreaseStock(List<CartDTO> cartDTOList);
    void increaseStock(List<CartDTO> cartDTOList);
    List<ProductInfo> findByProdcutCategory(Integer category);
    List<ProductInfo> findByProductId(List<String> productIdList);
    //上架
    public ProductInfo onSale(String productId);
    //下架
    public ProductInfo offSale(String productId);





}
