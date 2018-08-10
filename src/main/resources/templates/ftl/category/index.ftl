<#import "../spring.ftl" as spring>
<#include "../common/seller-header.ftl">
<#include "../common/seller-nav.ftl">
<#include "../common/seller-left-sider.ftl">
    <div id="seller-category-index" class="panel panel-default">
        <form role="form" method="post" action="/sell/seller/category/save">
            <div class="form-group">
                <label>名字</label>
                <input name="categoryName" type="text" class="form-control" value="${(category.categoryName)!''}"/>
            </div>
            <div class="form-group">
                <label>type</label>
                <input name="categoryType" type="number" class="form-control" value="${(category.categoryType)!''}"/>
            </div>
            <input hidden type="text" name="categoryId" value="${(category.categoryId)!''}">
            <div class="form-group pull-right">
                <button type="submit" class="btn btn-default">提交</button>
            </div>
        </form>
    </div>
</div>