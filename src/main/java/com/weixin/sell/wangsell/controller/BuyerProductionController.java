package com.weixin.sell.wangsell.controller;

import com.weixin.sell.wangsell.dataobject.ProductCategory;
import com.weixin.sell.wangsell.dataobject.ProductInfo;
import com.weixin.sell.wangsell.enums.ResultEnum;
import com.weixin.sell.wangsell.service.CategoryService;
import com.weixin.sell.wangsell.service.ProductService;
import com.weixin.sell.wangsell.utils.ProductInfo2ProductInfoVo;
import com.weixin.sell.wangsell.utils.ResultVoUtil;
import com.weixin.sell.wangsell.vo.ProductInfoVo;
import com.weixin.sell.wangsell.vo.ProductVo;
import com.weixin.sell.wangsell.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@Controller
@RequestMapping("/buyer/product")
@Slf4j
public class BuyerProductionController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    /*
     *返回商品的所有种类和某个种类的所有商品列表
     * @author wang
     * @date 2018/7/31 14:47
     * @param
     * @return
     */
    @GetMapping("/allList")
    @ResponseBody
    public ResultVo<List<ProductVo>> list(){
        List<ProductInfo> productInfoList = productService.findUpAll();
        List<Integer> categoryTypeList = productInfoList.stream().map(
                e-> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        List<ProductVo> productVoList = new ArrayList<ProductVo>() ;
        for(ProductCategory productCategory : productCategoryList){
            ProductVo productVo = new ProductVo();
            productVo.setCategoryType(productCategory.getCategoryType());
            productVo.setCategoryName(productCategory.getCategoryName());
            List<ProductInfoVo> productInfoVoList = new ArrayList<ProductInfoVo>();
            for(ProductInfo productInfo : productInfoList){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo,productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            }
            productVo.setProductInfoList(productInfoVoList);
            productVoList.add(productVo);
        }
        return ResultVoUtil.success(productVoList);
    }

    /*查询某个种类的所有商品列表
     *
     * @author wang
     * @date 2018/7/31 13:05
     * @param categoryType
     * @return productVo
     */
    @ResponseBody
    @GetMapping("/categoryList/{categoryType}")
    public ResultVo<ProductVo> productList(@PathVariable("categoryType")Integer categoryType){
        if(categoryType == null){
            categoryType = 1 ;
        }
        List<Integer> categoryList = Arrays.asList(categoryType);
        List<ProductCategory> productCategoryList= categoryService.findByCategoryTypeIn(categoryList);
        if(productCategoryList.size() == 0){
            log.error("【查询商品类型的列表】没有找到类型{}",categoryList);
            return ResultVoUtil.error(ResultEnum.PRODUCT_CATEGORY_NOT_EXIST.getCode(),"没有改种类商品");
        }
        ProductCategory productCategory =productCategoryList.get(0);
        List<ProductInfo> productInfoList = productService.findByProdcutCategory(productCategory.getCategoryType());
        ProductVo productVo = new ProductVo();
        List<ProductInfoVo> productInfoVoList = ProductInfo2ProductInfoVo.convert(productInfoList);
        productVo.setProductInfoList(productInfoVoList);
        productVo.setCategoryName(productCategory.getCategoryName());
        productVo.setCategoryType(productCategory.getCategoryType());
        return ResultVoUtil.success(productVo);
    }
    @RequestMapping("/productInfoDetail/{id}")
    public String singleProduct(@PathVariable("id")String id, Model model){
        ProductInfo productInfo = productService.findOne(id);
        if(productInfo == null ){
            model.addAttribute("errorMsg","查询的产品不存在");
            return "common/error";
        }
        model.addAttribute("productInfo",productInfo);
        return "product-detail";
    }


}
