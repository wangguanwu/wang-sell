package com.weixin.sell.wangsell.controller;/*
 * @author monster
 * @date 2018/8/2 13:01
 */

import com.weixin.sell.wangsell.controller.dto.CartDTO;
import com.weixin.sell.wangsell.enums.ResultEnum;
import com.weixin.sell.wangsell.utils.ResultVoUtil;
import com.weixin.sell.wangsell.vo.ResultVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    /*
     *添加商品收到购物车
     * @author wang
     * @date 2018/8/2 13:34
     * @param
     * @return
     */
    @RequestMapping("/addItems")
    public ResultVo addToCart(CartDTO cartDTO ,HttpServletRequest request , HttpServletResponse response){
        if(cartDTO == null|| cartDTO.getProductQuantity() == null|| cartDTO.getProductId() == null){
            return ResultVoUtil.error(ResultEnum.PARAM_ERROR.getCode(),"添加失败");
        }
        List<CartDTO> cartDTOList =(List<CartDTO>) request.getSession().getAttribute("cart");
        if(cartDTOList == null){
            cartDTOList = new ArrayList<>();
        }
        request.getSession().setAttribute("cart",cartDTOList);
        cartDTOList.add(cartDTO);
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
    public ResultVo removeCartItem(HttpServletRequest request){
        List<CartDTO> cartDTOList  = (List<CartDTO>)request.getSession().getAttribute("cart");
        if(cartDTOList!= null){
            request.getSession().removeAttribute("cart");
            cartDTOList.clear();
        }
        return ResultVoUtil.success("成功清空购物车");

    }
}
