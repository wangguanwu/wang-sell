package com.weixin.sell.wangsell.dataobject.mapper;/*
 * @author monster
 * @date 2018/8/13 8:49
 */

import com.weixin.sell.wangsell.dataobject.ProductCategory;
import org.apache.ibatis.annotations.*;

import java.util.Map;

public interface ProductCategoryMapper {
    @Insert("insert into product_category (category_name,category_type)values(#{category_name,jdbcType=VARCHAR},#{category_type,jdbcType=INTEGER})")
    public int insertByMap(Map<String,Object>map);
    @Select("select * from product_category where category_type=#{categoryType}")
    @Results({
            @Result(column = "category_type",property = "categoryType"),
            @Result(column = "category_name",property = "categoryName"),
            @Result(column = "category_id",property = "categoryId"),
    })
    ProductCategory findByCategoryType(Integer categoryType);
    @Update("update product_category set category_name=#{categoryName} where category_type=#{categoryType}")
    int updateByCategoryType(@Param("categoryType") Integer categoryType,@Param("categoryName") String categoryName);
    @Delete("delete from product_category where category_type = #{categoryType}")
    int deleteByType(Integer categoryType);
    ProductCategory ss( Integer categoryType);

}
