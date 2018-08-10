package com.weixin.sell.wangsell.config;/*
 * @author monster
 * @date 2018/8/9 18:43
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix="projecturl")
public class UrlConfig {
    public String sell ;
}
