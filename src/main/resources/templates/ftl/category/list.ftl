<#import "../spring.ftl" as spring>
<#include "../common/seller-header.ftl">
<#include "../common/seller-nav.ftl">
<#include "../common/seller-left-sider.ftl">
    <div class="panel panel-default" id="seller-category-list">
        <table class="table table-bordered table-condensed">
            <thead>
            <tr>
                <th>类目id</th>
                <th>名字</th>
                <th>type</th>
                <th>创建时间</th>
                <th>修改时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
                <#list categoryList as category>
                <tr>
                    <td>${category.categoryId}</td>
                    <td>${category.categoryName}</td>
                    <td>${category.categoryType}</td>
                    <td>${category.createTime}</td>
                    <td>${category.updateTime}</td>
                    <td><a href="/sell/seller/category/index?categoryId=${category.categoryId}">修改</a></td>
                </tr>
                </#list>
            </tbody>
        </table>
    </div>
</div>
<#--end root-page-->
