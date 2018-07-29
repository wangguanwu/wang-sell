package com.weixin.sell.wangsell.controller.dto;/*
 * @author wang
 * @date 2018/7/25 9:31
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.weixin.sell.wangsell.dataobject.OrderDetail;
import com.weixin.sell.wangsell.enums.OrderStatusEnum;
import com.weixin.sell.wangsell.enums.PayStatusEnum;
import com.weixin.sell.wangsell.utils.Date2LongSerializer;
import com.weixin.sell.wangsell.utils.EnumUtil;
import lombok.Data;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    private BigDecimal orderAmount;
    private Integer orderStatus;
    private Integer payStatus;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    List<OrderDetail> orderDetailList;
    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum() {
        return EnumUtil.getByteCode(orderStatus,OrderStatusEnum.class);
    }
    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getByteCode(payStatus,PayStatusEnum.class);
    }

}
