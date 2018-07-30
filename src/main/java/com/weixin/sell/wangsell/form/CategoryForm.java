package com.weixin.sell.wangsell.form;/*
 * @author monster
 * @date 2018/7/30 15:34
 */

import lombok.Data;

@Data
public class CategoryForm {
    private Integer categoryId;

    /** 类目名字. */
    private String categoryName;

    /** 类目编号. */
    private Integer categoryType;
}
