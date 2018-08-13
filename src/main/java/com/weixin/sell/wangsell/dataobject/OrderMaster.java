package com.weixin.sell.wangsell.dataobject;/*
 * @author wang
 * @date $
 */

import com.weixin.sell.wangsell.enums.OrderStatusEnum;
import com.weixin.sell.wangsell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
@DynamicInsert
public class OrderMaster implements Serializable {

    private static final long serialVersionUID = -2347245187489400509L;
    @Id
    private String orderId;
    private String buyerName;
    private String buyerAddress;
    private String  buyerOpenid;
    private BigDecimal orderAmount;
    private String buyerPhone;

    /*订单状态*/
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();
    /*支付状态*/
    private Integer payStatus = PayStatusEnum.WAIT.getCode();
     /*创建时间*/
    private Date createTime;
    /*更新时间*/
    private Date updateTime ;

}
