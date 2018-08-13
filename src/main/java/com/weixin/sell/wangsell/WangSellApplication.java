package com.weixin.sell.wangsell;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@MapperScan(basePackages = "com.weixin.sell.wangsell.dataobject.mapper")
public class WangSellApplication {

    public static void main(String[] args) {
        SpringApplication.run(WangSellApplication.class, args);
    }
}
