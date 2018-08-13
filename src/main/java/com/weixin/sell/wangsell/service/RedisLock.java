package com.weixin.sell.wangsell.service;/*
 * @author monster
 * @date 2018/8/13 10:55
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
@Component
@Slf4j
public class RedisLock {
    @Autowired
    StringRedisTemplate  redisTemplate;

    /*
     *获取锁
     * @author wang
     * @date 2018/8/13 11:07
     * @param key
     * @param value 当前时间和过期时间
     * @return
     */
    public boolean lock(String key ,String value){
        if(redisTemplate.opsForValue().setIfAbsent(key,value)){
            return true ;//获取锁成功
        }
        String currentValue = redisTemplate.opsForValue().get(key);
        //判断当前时间是否是过期
        if(!StringUtils.isEmpty(currentValue)&&Long.parseLong(currentValue)<System.currentTimeMillis()){
            String oldValue = redisTemplate.opsForValue().getAndSet(key,value);
            //重复判断是否有其他线程获得锁
            if(!StringUtils.isEmpty(oldValue)&&oldValue.equals(currentValue)){
                return true ;
            }
        }
        return false;//获取锁失败
    }

    /*
     *解锁
     * @author wang
     * @date 2018/8/13 11:47
     * @param key ,value
     * @return
     */
    public void unlock(String key ,String value){
        try {
            String currentValue = redisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        }catch (Exception e){
            log.error("【redis分布式锁】解锁异常");
        }
    }

}
