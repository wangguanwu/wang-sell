package com.weixin.sell.wangsell.controller;/*
 * @author wang
 * @date 2018/7/25 8:47
 */

import com.weixin.sell.wangsell.controller.dto.CartDTO;
import com.weixin.sell.wangsell.controller.dto.OrderDTO;
import com.weixin.sell.wangsell.converter.OrderForm2OrderDTOConverter;
import com.weixin.sell.wangsell.dataobject.ProductInfo;
import com.weixin.sell.wangsell.enums.ResultEnum;
import com.weixin.sell.wangsell.form.OrderForm;
import com.weixin.sell.wangsell.sell.SellException;
import com.weixin.sell.wangsell.service.BuyerService;
import com.weixin.sell.wangsell.service.OrderService;
import com.weixin.sell.wangsell.service.ProductService;
import com.weixin.sell.wangsell.service.impl.WebSocket;
import com.weixin.sell.wangsell.utils.ResultVoUtil;
import com.weixin.sell.wangsell.vo.ResultVo;
import freemarker.template.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/buyer/order")

@Slf4j
public class BuyerOrderController {


    @Autowired
    Configuration cfg;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private BuyerService buyerService;
    @Autowired
    private WebSocket webSocket ;

    @ResponseBody
    @PostMapping("/create")
    public ResultVo<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确，orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.converter(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO createResult = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());
        webSocket.sendMessage("你有新的订单");
        return ResultVoUtil.success(map);

    }

    @ResponseBody
    @GetMapping("/list")

    public ResultVo<List<OrderDTO>> list(@RequestParam("openid") String openid, @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】， openId为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest request = PageRequest.of(page, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid, request);
        return ResultVoUtil.success(orderDTOPage.getContent());
    }

    @ResponseBody
    @GetMapping("/detail")
    public ResultVo<OrderDTO> details(@RequestParam("openid") String openid, @RequestParam("orderId") String orderId) {

        OrderDTO orderDTO = buyerService.cancelOrder(openid, orderId);
        return ResultVoUtil.success(orderDTO);
    }

    //    @ResponseBody
    @RequestMapping("/cancel")
    public String cancel(Model model, @RequestParam("openid") String openid, @RequestParam("orderId") String orderId) {
        //todo not safety
        try {
            OrderDTO result = buyerService.cancelOrder(openid, orderId);
            if (result == null) {
               throw new SellException(ResultEnum.ORDER_CANCEL_FAIL);
            }
        } catch (SellException e) {
            log.error("取消订单失败，请重试");
            model.addAttribute("errorMsg", "取消订单失败");
            return "/common/error";
        }
        return "redirect:/buyer/order/myOrder/"+openid;

    }

    @RequestMapping("/pay")
    public String finishOrder(Model model, @RequestParam("openid") String openid, @RequestParam("orderId") String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        try {
            OrderDTO result = orderService.paid(orderDTO);

            if (result == null) {
                throw new SellException(ResultEnum.ORDER_PAY_FAIL);
            }
        }catch (SellException e){
            log.info("支付失败{}"+e.getMessage());
            model.addAttribute("errorMsg", "支付失败");
            return "common/error";
        }
        model.addAttribute("payMsg", "支付成功");
        return  "redirect:/buyer/order/myOrder/"+openid;
    }

    @RequestMapping("/createOrder/directBuy")
    public String createOneProductOrderPage(HttpServletRequest request, CartDTO cartDTO, Model model, Integer quantity) {
        if (cartDTO == null || cartDTO.getProductId() == null || cartDTO.getProductQuantity() == null) {
            model.addAttribute("errorMsg", "输入的信息非法");
            return "common/error";
        }
        ProductInfo productInfo = productService.findOne(cartDTO.getProductId());
        if (productInfo == null) {
            model.addAttribute("errorMsg", "查询的产品不存在");
            return "common/error";
        }
        model.addAttribute("cartDTO", cartDTO);
        model.addAttribute("productInfo", productInfo);
        model.addAttribute("req", request);
        return "orderPage";
    }

    /*
     *返回顾客的订单
     * @author wang
     * @date 2018/8/5 18:57
     * @param openid
     * @return
     */
    @RequestMapping("/myOrder/{openid}")

    public String getAllOrder(@PathVariable("openid") String openid, Model model) {
        PageRequest request = PageRequest.of(0, 10);
        List<OrderDTO> orderMasterList = orderService.findList(openid, request).getContent();
        model.addAttribute("orderDTOList", orderMasterList);
        return "UserOrderMaster";

    }


}
