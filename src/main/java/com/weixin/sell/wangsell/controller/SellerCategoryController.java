package com.weixin.sell.wangsell.controller;
/*
 * @author monster
 * @date 2018/7/30 15:31
 */

import com.weixin.sell.wangsell.dataobject.ProductCategory;
import com.weixin.sell.wangsell.form.CategoryForm;
import com.weixin.sell.wangsell.sell.SellException;
import com.weixin.sell.wangsell.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {
    @Autowired
    private CategoryService categoryService;

    /*
     *查询类目列表
     * @author wang
     * @date 2018/8/9 10:15
     * @param
     * @return
     */
    @GetMapping("/list")
    public String list(Model model){
        List<ProductCategory> categoryList = categoryService.findAll();
        model.addAttribute("categoryList",categoryList);
        return "category/list";
    }

    /*
     *增加或者修改类目
     * @author wang
     * @date 2018/8/9 10:19
     * @param
     * @return
     */
    @GetMapping("/index")
    public String index(Model model, @RequestParam(value="categoryId",required = false)Integer categoryId){
        if(categoryId!=null){
            ProductCategory productCategory = categoryService.findOne(categoryId);
            model.addAttribute("category",productCategory);
        }
        return "category/index";
    }
    @PostMapping("/save")
    public String save(Model model , @Valid CategoryForm form , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            model.addAttribute("errorMsg",bindingResult.getFieldError());
            model.addAttribute("url","/sell/seller/category/index");
            return "common/error";

        }
        ProductCategory productCategory = new ProductCategory();
        try{
            if(form.getCategoryId()!=null){
                productCategory = categoryService.findOne(form.getCategoryId());
            }
            BeanUtils.copyProperties(form,productCategory);
            categoryService.save(productCategory);
        }catch (SellException e){
            model.addAttribute("msg",e.getMessage());
            model.addAttribute("url","/sell/seller/category/index");
            return "common/error";
        }
        model.addAttribute("url","/sell/seller/category/list");
        return "common/success";
    }

}
