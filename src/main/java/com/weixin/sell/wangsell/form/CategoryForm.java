package com.weixin.sell.wangsell.form;/*
 * @author monster
 * @date 2018/7/30 15:34
 */

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CategoryForm {

    private Integer categoryId;

    /** 类目名字. */
    @NotBlank(message = "类目名字不能为空")
    private String categoryName;

    /** 类目编号. */
    @NotNull(message = "类目类型必须指定")
    private Integer categoryType;
}
