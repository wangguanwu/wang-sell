package com.weixin.sell.wangsell.service.impl;/*
 * @author monster
 * @date 2018/7/29 21:54
 */

import com.weixin.sell.wangsell.dataobject.SellerInfo;
import com.weixin.sell.wangsell.repository.SellerIInfoRepository;
import com.weixin.sell.wangsell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerIInfoRepository repository;

    @Override
    public SellerInfo findById(String id) {//根据id查找管理员
        return repository.findSellerInfoById(id);
    }

    @Override
    public List<SellerInfo> findByName(String name) {//根据名字查找管理员
        return repository.findSellerInfoByUsername(name);
    }

    @Transactional
    @Override
    public int update(SellerInfo sellerInfo) {//更新管理员信息
        SellerInfo result =repository.save(sellerInfo) ;
        return result!=null?1:0;
    }

    @Override
    public SellerInfo findByUsernameAndPassword(SellerInfo sellerInfo) {
        return repository.findSellerInfoByUsernameAndPassword(sellerInfo.getUsername(),sellerInfo.getPassword());
    }

    @Override
    public SellerInfo save(SellerInfo sellerInfo) {
        return  repository.save(sellerInfo);
    }
}
