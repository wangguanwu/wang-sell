package com.weixin.sell.wangsell.form;/*
 * @author monster
 * @date 2018/7/30 15:28
 */

import lombok.Data;

import java.math.BigDecimal;
@Data
public class ProductForm {
    private String productId;

    /** 名字. */
    private String productName;

    /** 单价. */
    private BigDecimal productPrice;

    /** 库存. */
    private Integer productStock;

    /** 描述. */
    private String productDescription;

    /** 小图. */
    private String productIcon;
}
