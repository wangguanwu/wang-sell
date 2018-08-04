<#import "spring.ftl" as spring>
<#include "./common/header.ftl">
<#include "./common/nav.ftl">


    </div><!-- total-page end-->
</div> <!-- root-page container -->
<div id="product-detail-wang" class="container">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">
            ${productInfo.productName}
            </h3>
        </div>

    <#--<div class="panel panel-default">-->
        <div class="panel-body">
            <div id="product-img">
                <img src="${productInfo.productIcon}/"/>
            </div>
            <div class="product-description">
            ${productInfo.productDescription}
            </div>
            <div class="price">
                ￥${productInfo.productPrice}
            </div>
        <#--</div>-->
        </div>
        <div class="btn-group">
            <div class="btn-group-inner">

                <button type="button" id="select-quantity"  data-toggle="modal" data-target="#myModal">购买</button>
            </div>
        </div>


    </div>
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        请选择购买的数量
                    </h4>
                </div>
                <div class="modal-body">
                    <form class="form">
                        <div class="form-group">
                            <label> 单价:￥${productInfo.productPrice}</label>
                        </div>
                        <div class="form-group">
                            <label for="quantity">数量</label>
                            <input type="text" class="form-control" id="quantity" placeholder="请输入数量"/>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button id="buy-imm" type="button" class="btn btn-default" data-dismiss="modal">立即购买
                    </button>
                    <button id="add-cart" type="button" class="btn btn-primary" data-dismiss="modal">
                        添加到购物车
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
</div>
</body>
</html>
<script type="text/javascript">
    $(function(){
        $("#add-cart").click(function(){//productId,productQuantity
            var pcounts = $('#quantity').val();
            var productId = ${productInfo.productId};

            $.ajax({
                url:'/sell/cart/addItems',
                method:'post',
                data: 'productId='+productId+'&'+'productQuantity='+pcounts,
                success:function(result){
                    if(result.code== 0){
                        alert("成功加入购物车");
                    }else{
                        alert("加入购物车失败");
                    }
                }
            })
        })
        $("#buy-imm").click(function(){
            var pcounts = $('#quantity').val();
            var productId = ${productInfo.productId};
            var queryParams= 'productId='+productId+'&'+'productQuantity='+pcounts;
            var paths = "/sell/buyer/order/createOrder/directBuy?"+queryParams ;
            window.location.href=paths;
        })
    })

</script>