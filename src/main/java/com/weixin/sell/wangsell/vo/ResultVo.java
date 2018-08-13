package com.weixin.sell.wangsell.vo;

import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;

@Data
public class ResultVo<T> implements Serializable {

    private static final long serialVersionUID = -8096379529046638974L;
    //    错误码
    private Integer code;
//    提示信息
    private String msg;
//    具体内容
    private T data;
}
