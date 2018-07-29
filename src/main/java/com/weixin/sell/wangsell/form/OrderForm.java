package com.weixin.sell.wangsell.form;/*
 * @author wang
 * @date 2018/7/25 8:50
 */

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class OrderForm {
    @NotEmpty(message = "姓名必填")
    private String name;
    @NotEmpty(message = "手机号必填")
    private String phone;
    @NotEmpty(message="地址必填")
    private String address;
    @NotEmpty(message="openid必填")
    private String openid;
    @NotEmpty(message = "购物车不为空")
    private String items;
}
