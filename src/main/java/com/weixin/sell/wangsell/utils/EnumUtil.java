package com.weixin.sell.wangsell.utils;/*
 * @author wang
 * @date 2018/7/25 10:12
 */

import com.weixin.sell.wangsell.enums.CodeEnum;

public class EnumUtil {
    public static <T extends CodeEnum> T getByteCode(Integer code,Class<T> enumClass){
        for(T each : enumClass.getEnumConstants()){
            if(code.equals(each.getCode())){
                return each ;
            }
        }
        return  null ;
    }
}
