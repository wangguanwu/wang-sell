package com.weixin.sell.wangsell.controller.dto;/*
 * @author wang
 * @date 2018/7/25 13:36
 */

import lombok.Data;

@Data

public class CartDTO {
    private String productId;
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

    public CartDTO() {
    }
}
