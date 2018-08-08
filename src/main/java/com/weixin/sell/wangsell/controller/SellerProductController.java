package com.weixin.sell.wangsell.controller;/*
 * @author monster
 * @date 2018/7/30 15:13
 */

import com.weixin.sell.wangsell.dataobject.ProductCategory;
import com.weixin.sell.wangsell.dataobject.ProductInfo;
import com.weixin.sell.wangsell.form.ProductForm;
import com.weixin.sell.wangsell.sell.SellException;
import com.weixin.sell.wangsell.service.CategoryService;
import com.weixin.sell.wangsell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/product")
public class SellerProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    /*
     *功能描述 列表
     * @author wang
     * @date 2018/7/30 15:27
     * @param productForm

     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value="page",defaultValue = "1")Integer page,
                             @RequestParam(value="size",defaultValue = "10")Integer size, Map<String,Object>map){
        PageRequest request = PageRequest.of(page-1,size);
        Page<ProductInfo> productInfoList = productService.findAll(request);
        map.put("productInfoPage",productInfoList);
        map.put("currentPage",page);
        map.put("size",size);

        return new ModelAndView("product/list",map);
    }

    /*
     *商品上架
     * @author wang
     * @date 2018/8/8 17:43
     * @param
     * @return
     */
    @RequestMapping("/onsale")
    public ModelAndView onSale(@RequestParam(value="productId")String productId,
                               Map<String,Object> map){
        try{
            productService.onSale(productId);
        }catch (SellException e){
            map.put("errorMsg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success");
    }

    /*
     *商品下架
     * @author wang
     * @date 2018/8/8 17:44
     * @param
     * @return
     */
    @RequestMapping("/offsale")
    public ModelAndView offSale(@RequestParam(value="productId")String productId,
                               Map<String,Object> map){
        try{
            productService.offSale(productId);
        }catch (SellException e){
            map.put("errorMsg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }
    @GetMapping("/index")
    public String index(@RequestParam(value="productId",required = false)String productId,
                        Model model){
        if(!StringUtils.isEmpty(productId)){
            ProductInfo productInfo = productService.findOne(productId);
            model.addAttribute("productInfo",productInfo);
        }
        List<ProductCategory>  categoryList =categoryService.findAll();
        model.addAttribute("categoryList",categoryList);
        return "product/index";
    }
}
