<#import "spring.ftl" as spring>
<#include "common/header.ftl">
<#include "common/nav.ftl">
    </div>
</div>
<div class="modify-cart-page">
    <div class="panel" id="form-panel">
        <form action="/sell/cart/modifyCart" method="post">
            <div hidden>
                <input type="text" name="productId" value="${productInfo.productId!""}"/>
            </div>
            <div class="form-group">
                <table class="table table-black">
                    <caption style="text-align: center;font-size:2em;">${productInfo.productName}</caption>
                    <thead>
                    <tr>
                        <td>商品名称</td>
                        <td>商品数量</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>${productInfo.productName!""}</td>
                        <td><input type="text" name="productQuantity" value="${cartDTO.productQuantity!""}"/></td>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="from-group" id="button-group-modify">
                <button class="btn btn-primary">修改</button>
            </div>
        </form>
    </div>
</div>
