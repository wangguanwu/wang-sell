package com.weixin.sell.wangsell.form;/*
 * @author monster
 * @date 2018/7/30 15:28
 */

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
@Data
public class ProductForm {

    private String productId;

    /** 名字. */
    @NotBlank(message = "商品名字不能为空")
    private String productName;

    /** 单价. */
    @NotNull(message = "商品价格不能为空")
    private BigDecimal productPrice;

    /** 库存. */
    @NotNull(message = "商品库存不能为空")
    private Integer productStock;

    /** 描述. */

    private String productDescription;

    /** 小图. */
    private String productIcon;
    /**商品类别*/
    @NotNull(message = "商品类别不能为空")
    private Integer categoryType;
}
