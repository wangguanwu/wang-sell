package com.weixin.sell.wangsell.enums;/*
 * @author wang
 * @date $
 */

import lombok.Getter;

@Getter
public enum ResultEnum {
    SUCCESS(0,"成功"),
    PARAM_ERROR(1,"参数错误"),
    PRODUCT_NOT_EXIST(2,"商品不存在"),
    PRODUCT_STOCK_ERROR(3,"商品库存错误"),
    ORDER_NOT_EXIST(4,"订单不存在"),
    ORDERDETAIL_NOT_EXIST(5,"订单详情不存在"),
    ORDER_STATUS__ERROR(7,"订单状态不正确"),
    ORDER_UPDATE_ERROR(8,"订单更新失败"),
    ORDER_DETAIL_EMPTY(9,"订单详情为空"),
    ORDER_PAY_STATUS_ERROR(10,"订单支付状态不正确"),
    CART_EMPTY(11,"购物车为空"),
    ORDER_OWNER_ERROR(12,"该订单不属于当前用户"),
    WECHAT_MP_ERROR(13,"微信公众号错误"),
    WXPAY_NOTIFY_MONEY_VERIFY_ERROR(14, "微信支付异步通知金额校验不通过"),

    ORDER_CANCEL_SUCCESS(15, "订单取消成功"),

    ORDER_FINISH_SUCCESS(16, "订单完结成功"),

    PRODUCT_STATUS_ERROR(17, "商品状态不正确"),

    LOGIN_FAIL(18, "登录失败, 登录信息不正确"),

    LOGOUT_SUCCESS(19, "登出成功"),
    SELLERINFO_UPDATE_ERROR(20,"管理员更新失败"),
    SELLER_ADD_ERROR(21,"管理员增加失败"),
    PRODUCT_CATEGORY_NOT_EXIST(22,"商品类型不存在"),

    ;
    private Integer code ;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
