package com.weixin.sell.wangsell.dataobject;/*
 * @author monster
 * @date 2018/7/29 21:21
 */

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.DateTimeException;
import java.util.Date;

@Entity
@Data
@DynamicInsert
@DynamicUpdate
public class SellerInfo {
    @Id
    @GeneratedValue
    private  Integer id;
    private String username ;
    private String password;
    private String openid;
    private Date createTime;
    private Date updateTime;

}
