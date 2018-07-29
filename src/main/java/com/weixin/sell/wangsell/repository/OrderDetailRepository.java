package com.weixin.sell.wangsell.repository;/*
 * @author wang
 * @date $
 */

import com.weixin.sell.wangsell.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
    public List<OrderDetail> findByOrderId(String orderId);
}
