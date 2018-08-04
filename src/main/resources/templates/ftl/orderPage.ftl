<#import "spring.ftl" as spring>
<#include "common/header.ftl">
<#include "common/nav.ftl">
    </div><!-- total-page-->
</div><!--root page-->
<div id="order-out">
    <div id="order-page-show" class="panel col-sm-10">
        <form   method="post"  action="buyer/order/create">
            <div class="form-group">
                <label for="openid">openid</label>
                <input type="text" name="openid" class="form-control" id="openid" placeholder="请输入您的有效电话" value="${openid!""}"/>
            </div>
            <div class="form-group">
                <label for="name">姓名</label>
                <input type="text" name="name" class="form-control" id="name" placeholder="请输入您的姓名" value="${name!""}"/>
            </div>
            <div class="form-group">
                <label for="phone">电话</label>
                <input type="text" name="phone" class="form-control" id="phone" placeholder="请输入您的有效电话" value="${phone!""}"/>
            </div>
            <div class="form-group">
                <label for="phone">地址</label>
                <input type="text" name="address" class="form-control" id="address" placeholder="请输入您的有效地址" value="${address!""}"/>
            </div>
            <div hidden>
                <input type="text" name="items" title="购物车的商品数据" id="items"/>
            </div>
            <div class="form-group" id="button-group">
                <div>
                    <button type="submit" class="btn btn-default">提交订单</button>
                </div>
            </div>
        </form>
    </div>
    <div style="display:none;" class="alert alert-warning">
        <a href="#" class="close" data-dismiss="alert">
            &times;
        </a>
        <strong>警告！</strong>你购物车没有任何商品
    </div>
</div>

<script type="text/javascript">
    $(function(){
        //首先获得购物车的数据
        var productId = ${Session['productId']!false};
        var productQuantiry = ${Session['productQuantity']!false};
        if(!productId ||!productQuantiry)
        {
            $(".alert .alert-warning").css("display","none");
            return ;
        }else{
            $(".alert .alert-warning").css("display","block");
        }
        var postdata =""
        var array = $('form').serializeArray()
        $.each(t,function(){
            postdata=postdata+this.name+"="+this.value+"&";
        });
        var orderData = {};
        orderData['productId'] = productId;
        orderData['productQuantity'] = productQuantiry;
        postdata= postdata+"items=["+JSON.stringify(orderData)+"]";


        $(".btn .btn-default").click(function(){
            $.ajax({
                method:'POST',
                data:postdata,
                success:function(result){
                    alert("创建订单成功"+result+"将返回主页")
                    window.location.href="/";
                }
            })
        })
    })
</script>