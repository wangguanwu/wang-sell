package com.weixin.sell.wangsell.controller;/*
 * @author monster
 * @date 2018/7/30 15:13
 */

import com.weixin.sell.wangsell.form.ProductForm;
import com.weixin.sell.wangsell.service.CategoryService;
import com.weixin.sell.wangsell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/seller/product")
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
    @PostMapping("/save")
    public String save(@Valid ProductForm productForm , BindingResult bindingResult, Model model){{
        //todo
        return null ;
    }

    }

}
