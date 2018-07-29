package com.weixin.sell.wangsell.sell;/*
 * @author wang
 * @date 2018/7/25 8:59
 */

import com.weixin.sell.wangsell.enums.ResultEnum;

public class SellException extends RuntimeException{
    private Integer code;
    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
    }
    public SellException(Integer code,String message){
        super(message);
        this.code = code ;
    }
}
