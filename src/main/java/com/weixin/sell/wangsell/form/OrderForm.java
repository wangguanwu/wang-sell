package com.weixin.sell.wangsell.form;/*
 * @author wang
 * @date 2018/7/25 8:50
 */

import lombok.Data;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class OrderForm {
    @NotBlank(message = "姓名必填")
    private String name;
    @NotBlank(message = "手机号必填")
    private String phone;
    @NotBlank(message="地址必填")
    private String address;
    @NotBlank(message="openid必填")
    private String openid;
    @NotBlank(message = "购物车不为空")
    private String items;
}
