package com.weixin.sell.wangsell.repository;/*
 * @author monster
 * @date 2018/7/29 21:52
 */

import com.weixin.sell.wangsell.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellerIInfoRepository extends JpaRepository<SellerInfo ,String> {
    SellerInfo findSellerInfoById(String  id);
    List<SellerInfo> findSellerInfoByUsername(String name);
    SellerInfo findSellerInfoByOpenid(String openid);
    SellerInfo findSellerInfoByUsernameAndPassword(String username ,String password);

}
