<#import "../spring.ftl" as spring>
<#include "../common/seller-header.ftl">
<#include "../common/seller-nav.ftl">
<#include "../common/seller-left-sider.ftl">
<#--主要内容-->
    <div id="product-modify-content" class="panel panel-default">
        <div class="container-fluid" class="panel">
            <div class="row clearfix">
                <form role="form" method="post" style="margin-left:10px;margin-right:10px;" action="/sell/seller/product/save">
                    <div class="form-group">
                        <label>名称</label>
                        <input type="text" name="productName" class="form-control" value="${productInfo.productName!''}"/>
                    </div>
                    <div class="form-group">
                        <label>价格</label>
                        <input name="productPrice" type="text" class="form-control"
                               value="${(productInfo.productPrice)!''}"/>
                    </div>
                    <div class="form-group">
                        <label>库存</label>
                        <input name="productStock" type="number" class="form-control"
                               value="${(productInfo.productStock)!''}"/>
                    </div>
                    <div class="form-group">
                        <label>描述</label>
                        <input name="productDescription" type="text" class="form-control"
                               value="${(productInfo.productDescription)!''}"/>
                    </div>
                    <div class="form-group">
                        <label>图片</label>
                        <img height="100" width="100" src="${(productInfo.productIcon)!''}" alt="">
                        <input name="productIcon" type="text" class="form-control"
                               value="${(productInfo.productIcon)!''}"/>
                    </div>
                    <div class="form-group">
                        <label>类目</label>
                        <select name="categoryType" class="form-control">
                                <#list categoryList as category>
                                    <option value="${category.categoryType}"
                                            <#if (productInfo.categoryType)?? && productInfo.categoryType == category.categoryType>
                                                selected
                                            </#if>
                                    >${category.categoryName}
                                    </option>
                                </#list>
                        </select>
                    </div>
                    <input hidden type="text" name="productId" value="${(productInfo.productId)!''}">
                    <button type="submit" class="btn btn-default">提交</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>