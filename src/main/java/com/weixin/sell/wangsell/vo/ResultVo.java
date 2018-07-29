package com.weixin.sell.wangsell.vo;

import lombok.Data;

import javax.persistence.Entity;

@Data
public class ResultVo<T>{
//    错误码
    private Integer code;
//    提示信息
    private String msg;
//    具体内容
    private T data;
}
