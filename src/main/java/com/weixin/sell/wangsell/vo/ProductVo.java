package com.weixin.sell.wangsell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.weixin.sell.wangsell.dataobject.ProductInfo;
import lombok.Data;

import javax.persistence.Entity;
import java.util.List;


@Data
public class ProductVo {

    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")
    private Integer categoryType;
    @JsonProperty("foods")
    private List<ProductInfoVo> productInfoList;
}
