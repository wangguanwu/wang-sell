package com.weixin.sell.wangsell.dataobject;/*
 * @author monster
 * @date 2018/7/29 21:21
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
public class SellerInfo {
    @Id
    private  String id;
    private String username ;
    private String password;
    private String openid;
    private Date createTime;
    private Date updateTime;
    public boolean isPasswordEqual(SellerInfo sellerInfo){
        if(sellerInfo!=null){
            return false  ;
        }
        return password.equals(sellerInfo.password);
    }

}
