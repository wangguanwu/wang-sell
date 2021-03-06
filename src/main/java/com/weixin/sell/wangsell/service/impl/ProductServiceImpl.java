package com.weixin.sell.wangsell.service.impl;

import com.weixin.sell.wangsell.controller.dto.CartDTO;
import com.weixin.sell.wangsell.dataobject.ProductInfo;
import com.weixin.sell.wangsell.enums.ProductStatusEnum;
import com.weixin.sell.wangsell.enums.ResultEnum;
import com.weixin.sell.wangsell.repository.ProductInfoRepository;
import com.weixin.sell.wangsell.sell.SellException;
import com.weixin.sell.wangsell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Cacheable(cacheNames = "product",key="123")
    @Override
    public ProductInfo findOne(String productId) {

        return productInfoRepository.findById(productId).get();
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {

        return productInfoRepository.findAll(pageable);
    }

    @Override
    @CachePut(cacheNames = "product",key="123")
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }


    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO:cartDTOList){
            ProductInfo productInfo = productInfoRepository.findById(cartDTO.getProductId()).get();
            if(productInfo  == null )
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            Integer result = productInfo.getProductStock()+cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }
    }
    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO: cartDTOList) {
            ProductInfo productInfo = productInfoRepository.findById(cartDTO.getProductId()).get();
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);

            productInfoRepository.save(productInfo);
        }
    }

    @Override
    public List<ProductInfo> findByProdcutCategory(Integer category) {
        return productInfoRepository.findByCategoryType(category);
    }

    @Override
    @Cacheable(cacheNames = "product",key = "123")
    public List<ProductInfo> findByProductId(List<String> productIdList) {
        return productInfoRepository.findByProductId(productIdList);
    }
    //上架
    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo result =productInfoRepository.findById(productId).get();
        if(result == null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if(result.getProductStatus()== ProductStatusEnum.UP.getCode()){
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        result.setProductStatus(ProductStatusEnum.UP.getCode());
        return productInfoRepository.save(result);
    }
    //下架
    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo result =productInfoRepository.findById(productId).get();
        if(result == null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if(result.getProductStatus()== ProductStatusEnum.DOWN.getCode()){
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        result.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return productInfoRepository.save(result);
    }
}
