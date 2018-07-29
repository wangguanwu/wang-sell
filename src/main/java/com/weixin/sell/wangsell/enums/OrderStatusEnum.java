package com.weixin.sell.wangsell.enums;/*
 * @author wang
 * @date $
 */

public enum OrderStatusEnum implements CodeEnum{
    NEW(0,"新订单"),
    FINISHED(1,"订单完成"),
    CANCEL(2,"已取消");
    private Integer code;
    private String message;
    OrderStatusEnum(Integer code , String message ) {
        this.code = code ;
        this.message = message ;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
