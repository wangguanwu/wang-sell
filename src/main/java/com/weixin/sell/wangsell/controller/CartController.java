package com.weixin.sell.wangsell.controller;/*
 * @author monster
 * @date 2018/8/2 13:01
 */

import com.mysql.cj.Session;
import com.weixin.sell.wangsell.controller.dto.CartDTO;
import com.weixin.sell.wangsell.dataobject.ProductInfo;
import com.weixin.sell.wangsell.enums.ResultEnum;
import com.weixin.sell.wangsell.form.Customer;
import com.weixin.sell.wangsell.service.ProductService;
import com.weixin.sell.wangsell.utils.ResultVoUtil;
import com.weixin.sell.wangsell.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/cart")
@Slf4j
public class CartController {
    @Autowired
    private ProductService productService;

    /*
     *添加商品收到购物车
     * @author wang
     * @date 2018/8/2 13:34
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/addItems")
    public ResultVo addToCart(CartDTO cartDTO, HttpServletRequest request, HttpServletResponse response) {
        if (cartDTO == null || cartDTO.getProductQuantity() == null || cartDTO.getProductId() == null) {
            return ResultVoUtil.error(ResultEnum.PARAM_ERROR.getCode(), "添加失败");
        }
        Map<String, Integer> cartSimple = (Map<String, Integer>) request.getSession().getAttribute("cart");
        Map<ProductInfo, Integer> cart = (Map<ProductInfo, Integer>) request.getSession().getAttribute("cartMap");
        if (cartSimple == null) {
            cartSimple = new HashMap<>();
            request.getSession().setAttribute("cart", cartSimple);
        }
        if (cart == null) {
            cart = new HashMap<>();
            request.getSession().setAttribute("cartMap", cart);
        }
        ProductInfo productInfo1 = productService.findOne(cartDTO.getProductId());
        String productId = cartDTO.getProductId();
        Integer quantity = cartDTO.getProductQuantity();

        //检查购物车是否存在该product
        if (cartSimple.containsKey(productId)) {
            cartSimple.put(productId, cartSimple.get(productId) + quantity);
            cart.put(productInfo1, cart.get(productInfo1) + quantity);
        } else {
            cartSimple.put(productId, quantity);
            cart.put(productInfo1, quantity);
        }

        return ResultVoUtil.success("成功添加商品到购物车");
    }
    /*
     *清空购物车
     * @author wang
     * @date 2018/8/2 13:34
     * @param
     * @return
     */

    @GetMapping("/removeAllCart")
    public ResultVo removeCartItem(HttpServletRequest request) {
        List<CartDTO> cartDTOList = (List<CartDTO>) request.getSession().getAttribute("cart");
        if (cartDTOList != null) {
            request.getSession().removeAttribute("cartMap");
            request.getSession().removeAttribute("cart");
            cartDTOList.clear();
        }
        return ResultVoUtil.success("成功清空购物车");

    }

    /*
     *返回购物车的所有商品信息和购物车的数量
     * @author wang
     * @date 2018/8/4 20:10
     * @param request,model
     * @return
     */
    @RequestMapping("/createOrder/cartBuy")
    public String createCartOrderPage(HttpServletRequest request, Model model) {
//        Map<String, Integer> map = (Map<String, Integer>) request.getSession().getAttribute("cart");
//        if (map == null) {
//            log.info("【获得购物车商品】购物车为空");
//            model.addAttribute("errorMsg", "购物车为空");
//            return "common/error";
//        }
//        Set<String> productIdSet = map.keySet();
//        List<String> list = new ArrayList<>(productIdSet.size());
//        productIdSet.stream().forEach(e -> {
//            list.add(e);
//        });
//        List<ProductInfo> result = productService.findByProductId(list);
        Map<ProductInfo, Integer> resultMap =(Map<ProductInfo, Integer>) request.getSession().getAttribute("cartMap");
        if(resultMap == null){
            resultMap = new HashMap<>();
            request.getSession().setAttribute("cartMap",resultMap);
        }
//        for (ProductInfo productInfo : result) {
//            for (String id : list) {
//                if (id.equals(productInfo.getProductId())) {
//                    resultMap.put(productInfo, map.get(id));//商品和商品数量
//                    break;
//                }
//            }
//        }
        model.addAttribute("cartMap", resultMap);
        return "cartOrderPage";
    }

    //查看购物车
    @RequestMapping("/cartDetail")
    public String readCart(Model model, HttpServletRequest request) {
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        HttpSession session = request.getSession();
        Map<ProductInfo ,Integer> map = (Map<ProductInfo,Integer>)session.getAttribute("cartMap");


        if (customer != null) {
            model.addAttribute("customer", customer);
        }
      //  model.addAttribute("cartMap",request.getSession().getAttribute("cartMap"));
        return "PersonalCart";

    }
}
