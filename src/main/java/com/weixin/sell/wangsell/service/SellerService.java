package com.weixin.sell.wangsell.service;/*
 * @author monster
 * @date 2018/7/29 21:54
 */

import com.weixin.sell.wangsell.dataobject.SellerInfo;

import java.util.List;

public interface SellerService {
    SellerInfo findByOpenId(String openid);
    SellerInfo findById(String id);
    List<SellerInfo> findByName(String id);
    int update(SellerInfo sellerInfo);
    SellerInfo findByUsernameAndPassword(SellerInfo sellerInfo);
    SellerInfo save(SellerInfo sellerInfo);
}
