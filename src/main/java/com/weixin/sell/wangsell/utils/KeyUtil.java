package com.weixin.sell.wangsell.utils;/*
 * @author wang
 * @date 2018/7/25 13:14
 */

import java.util.Random;

public class KeyUtil {
    public static synchronized String genUniqKey(){
        Random random = new Random();
        System.currentTimeMillis();
        Integer a =random.nextInt(900000)+100000;
        return System.currentTimeMillis()+String.valueOf(a);
    }
}
