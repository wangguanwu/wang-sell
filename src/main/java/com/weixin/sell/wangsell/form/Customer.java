package com.weixin.sell.wangsell.form;/*
 * 顾客信息
 * @author monster
 *
 * @date 2018/8/5 18:47
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer{
    private String openid;
    @NotEmpty(message = "姓名不能为空")
    private String name ;
    @NotEmpty(message = "密码不能为空")
    private String password;
    private String address ;
    private String phoneNumber ;
}
