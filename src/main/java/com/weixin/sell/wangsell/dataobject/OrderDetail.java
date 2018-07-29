package com.weixin.sell.wangsell.dataobject;/*
 * @author wang
 * @date 2018$
 */

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
@DynamicInsert
@DynamicUpdate
public class OrderDetail {
    @Id
    private String detailId;
    private String orderId;
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    /*商品数量*/
    private Integer productQuantity;
    private String productIcon;
}

