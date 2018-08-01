package com.weixin.sell.wangsell.utils;/*
 * @author monster
 * @date 2018/7/31 14:40
 *商品转换工具类
 */

import com.weixin.sell.wangsell.dataobject.ProductInfo;
import com.weixin.sell.wangsell.vo.ProductInfoVo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductInfo2ProductInfoVo {
    public static ProductInfoVo convert(ProductInfo productInfo){
        ProductInfoVo productInfoVo = new ProductInfoVo();
        BeanUtils.copyProperties(productInfo ,productInfoVo);
        return productInfoVo ;
    }

    /*
     *将productInfoList转换为productInfoVoList
     * @author wang
     * @date 2018/7/31 14:46
     * @param
     * @return
     */
    public static List<ProductInfoVo> convert(List<ProductInfo> productInfoList){
        List<ProductInfoVo> productInfoVoList = new ArrayList<>();
        for(ProductInfo productInfo : productInfoList){
                ProductInfoVo productInfoVo = new ProductInfoVo();
                BeanUtils.copyProperties(productInfo,productInfoVo);
                productInfoVoList.add(productInfoVo);
        }
        return  productInfoVoList ;
    }
}
