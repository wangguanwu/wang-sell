<#import "spring.ftl" as spring>
<#include "common/header.ftl">
<#include "common/nav.ftl">
    </div><!-- total-page-->
</div><!--root page-->
<div class="table-responsive">
    <table class="table">
        <caption>订单主表</caption>
        <thead>
        <tr>
            <th>订单id</th>
            <th>顾客姓名</th>
            <th>顾客电话</th>
            <th>顾客地址</th>
            <th>顾客openid</th>
            <th>订单金额</th>
            <th>订单状态</th>
            <th>支付状态</th>
            <th>创建时间</th>
            <th>更新时间</th>
            <th>取消订单</th>
            <th>完成支付</th>
        </tr>
        </thead>
        <tbody>
        <#if orderDTOList ??>
            <#list orderDTOList as item>
                <tr>
                    <td>${item.orderId}</td>
                    <td>${item.buyerName}</td>
                    <td>${item.buyerPhone}</td>
                    <td>${item.buyerAddress}</td>
                    <td>${item.buyerOpenid}</td>
                    <td>${item.orderAmount}</td>
                    <td>
                        <#if item.orderStatus == 0>新订单</#if>
                        <#if item.orderStatus == 1>已完成</#if>
                        <#if item.orderStatus == 2>取消</#if>
                    </td>
                    <td>
                        <#if item.payStatus == 0>等待支付</#if>
                        <#if item.payStatus == 1>支付成功</#if>
                    </td>
                    <td>${item.createTime?datetime}</td>
                    <td>${item.updateTime?datetime}</td>
                    <td>
                        <#if item.orderStatus == 0 ><a href="/sell/buyer/order/cancel?orderId=${item.orderId!""}&openid=${item.buyerOpenid!""}">取消订单</a></#if>
                        <#if item.orderStatus == 1>已完成订单</#if>
                        <#if item.orderStatus == 2>订单已经被取消</#if>
                    </td>
                    <td>
                        <#if item.payStatus == 0><a href="/sell/buyer/order/pay?orderId=${item.orderId!""}&openid=${item.buyerOpenid!""}">完成支付</a></#if>
                        <#if item.payStatus !=0>已完成</#if>
                    </td>
                </tr>
            </#list>
        </#if>
        </tbody>
    </table>
</div>