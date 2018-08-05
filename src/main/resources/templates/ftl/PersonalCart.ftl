<#import "spring.ftl" as spring>
<#include "common/header.ftl">
<#include "common/nav.ftl">
    </div><!-- total-page-->
</div><!--root page-->
<#--显示购物车的页面-->
<div class="cartMap">

    <table class="table table-striped">
        <caption>条纹表格布局</caption>
        <thead>
        <tr>
            <th>商品名称</th>
            <th>商品数量</th>
            <th>删除</th>
            <th>修改</th>
        </tr>
        </thead>
        <tbody>
        <#if cartMap??>
            <#list cartMap as k ,v>
                <tr>
                    <td>${k.productName}</td>
                    <td>${v}</td>
                    <td><a href="/cart/delete/${k.productId}">删除</a></td>
                    <td><a href="/cart/modify/${k.productId}">修改</a/></td>
                </tr>
            </#list>
        </#if>
        </tbody>
    </table>
</div>