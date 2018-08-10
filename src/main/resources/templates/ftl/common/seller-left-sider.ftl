<div class="total-page" id="total-page">
    <div class="left-sider-bar" id="seller-side-bar">
        <div class="panel-group" id="group-category">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#group-category"
                           href="#order-target">
                            订单
                        </a>
                    </h4>
                </div>
                <div id="order-target" class="panel-collapse collapse ">
                    <div class="panel-body">
                        <ul class="list-group">
                            <li class="list-group-item"><a href="/sell/seller/order/list">列表</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#group-category"
                           href="#demo0">
                            商品
                        </a>
                    </h4>
                </div>
                <div id="demo0" class="panel-collapse collapse ">
                    <div class="panel-body">
                        <ul class="list-group">
                            <li class="list-group-item"><a href="/sell/seller/product/list">列表</a></li>
                            <li class="list-group-item"><a href="/sell/seller/product/index">新增</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#group-category"
                           href="#demo1">
                           商品类别
                        </a>
                    </h4>
                </div>
                <div id="demo1" class="panel-collapse collapse ">
                    <div class="panel-body">
                        <ul class="list-group">
                            <li class="list-group-item"><a href="/sell/seller/category/list">列表</a></li>
                            <li class="list-group-item"><a href="/sell/seller/category/index">新增</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#group-category"
                           href="#logout">
                            用户管理
                        </a>
                    </h4>
                </div>
                <div id="logout" class="panel-collapse collapse ">
                    <div class="panel-body">
                        <ul class="list-group">
                            <li class="list-group-item"><a href="/sell/seller/logout">注销</a></li>
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

