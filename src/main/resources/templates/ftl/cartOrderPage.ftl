<#import "spring.ftl" as spring>
<#include "common/header.ftl">
<#include "common/nav.ftl">
    </div><!-- total-page-->
</div><!--root page-->
<div id="order-out">
    <div id="order-page-show" class="panel col-sm-10">
        <form>
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
            <div class="form-group">
                <div id="cart-product" class="panel">
                    <table class="table">
                        <caption>您将要购买的商品</caption>
                        <thead>
                        <tr>
                            <th>商品名称</th>
                            <th>商品单价</th>
                            <th>商品数量</th>
                        </tr>
                        </thead>
                        <tbody>
                          <#if cartMap??>
                              <#list cartMap as k , v>
                                  <tr>
                                      <td>${k.productName}</td>
                                      <td>${k.productInfo.productPrice}</td>
                                      <td>${v}</td>
                                  </tr>
                              </#list>
                          </#if>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="form-group" id="button-group">
                <div>
                    <button id="submit-order" class="btn btn-default">提交订单</button>
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

        $("#submit-order").click(function(e){
            e.preventDefault() ;//阻止默认行为
            if(!productId ||!productQuantiry)
            {
                $(".alert .alert-warning").css("display","none");
                return ;
            }else{
                $(".alert .alert-warning").css("display","block");
            }
            var postdata =""
            var array = $('form').serializeArray()
            $.each(array,function(){
                postdata=postdata+this.name+"="+this.value+"&";
            });
            var orderData = [];
            <#if cartMap??>
                <#list cartMap as k ,v>
                    var obj={};
                    obj['productId'] = '${k.productId}';
                    obj['productQuantity'] = '${v}' ;
                    orderData.push(obj);
                </#list>
            </#if>
            postdata= postdata+"items="+JSON.stringify(orderData);
            $.ajax({
                method:'POST',
                url:'/sell/buyer/order/create',
                data:postdata,
                success:function(result){
                    alert("创建订单成功"+result+"将返回主页");
                    window.location.href="/sell";
                }
            })
        })
    })
</script>