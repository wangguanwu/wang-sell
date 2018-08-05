<div class="total-page">
    <div class="left-sider-bar">
        <div class="panel-group" id="group-category">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#group-category"
                           href="#demo">
                            商品种类
                        </a>
                    </h4>
                </div>
                <div id="demo" class="panel-collapse collapse in">
                    <div class="panel panel-body">
                        <ul class="list-group">
                                <#assign product=productVoList>
                                <#list product as item>
                                <#--<#list item.productInfoList as productInfoList>-->
                                <#--</#list>-->
                                    <li class="list-group-item"><a href="buyer/product/categoryList">${item.categoryName}</a></li>
                                </#list>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#group-category"
                           href="#demo1">
                            <#assign person = user!false>
                            <#if person>
                                ${user.name}的空间
                            </#if>
                            <#if person == false>
                                我的空间
                            </#if>
                        </a>
                    </h4>
                </div>
                <div id="demo1" class="panel-collapse collapse in">
                    <div class="panel panel-body">
                        <ul class="list-group">
                            <li class="list-group-item"><a href="/sell/cart/cartDetail">查看购物车</a></li>
                            <li class="list-group-item"><a href="/sell/buyer/order/myOrder/<#if Session['customer']??>${Session['customer'].openid}</#if>">我的订单</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div> <!-- end left-side-bar-->
    <script type="text/javascript">
        $(function(){
            $("#check-cart").click(function(){
                window.location.href="/sell/cart/cartDetail";
            })
            $("#check-order").click(function(){
                window.location.href="/sell/buyer/order/myOrder/<#if Session['customer']??>${Session['customer'].openid}</#if>";
            })
        })
    </script>

