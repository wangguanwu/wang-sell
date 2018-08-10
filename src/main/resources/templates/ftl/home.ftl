<#import "spring.ftl" as spring>
<#include "common/header.ftl">
<#include "common/nav.ftl">
<#include "common/left-sider.ftl">
            <div class="root-container">
                <#if productVoList ??>
                        <#list productVoList as productVo>
                            <#if productVo.productInfoList??>
                              <#list productVo.productInfoList as productInfoVo>
                                 <div class="panel panel-default">
                                     <div class="panel-heading">
                                         <h3 class="panel-title">
                                             ${productInfoVo.productName!""}
                                         </h3>
                                     </div>
                                     <div class="panel-body">
                                         <div class="img-div">
                                             <img src="${productInfoVo.productIcon!""}" class="img-circle"/>
                                         </div>
                                         <div id="tail">
                                             价格:${productInfoVo.productPrice!""}
                                         </div>
                                     </div>
                                     <div class="panel-footer">
                                      <a class="btn btn-primary" type="button" href="buyer/product/productInfoDetail/${productInfoVo.productId!""}">详情</a>
                                     </div>
                                 </div>
                            </#list>
                            </#if>
                        </#list>
                </#if>

            </div><!-- end root-container 这里表示显示商品页面的容器-->
        </div> <!--end .total-page 这里表示除了导航页面的整个页面-->
    </div><#--root-page -->
</body>
 <link rel="stylesheet" href="<@spring.url'/css/root.css'/>"/>
 <link rel="stylesheet" href="<@spring.url'/css/home.css'/>"/>
<script type="text/javascript">
    $("document").ready(function () {
        // $.ajax({
        //     url: "buyer/product/categoryList/2",
        //     method: 'GET',
        //     success: function (result) {
        //         var productList = result.data['foods'];
        //         productList.forEach(function (item) {
        //             $(".root-container").append(
        //                     "<div class='panel panel-default'>" +
        //                     "<div class='panel-heading'>" +
        //                     "<h3 class='panel-title'>" + item.name + "</h3>" +
        //                     "</div>" +
        //                     "<div id='panel-body' class='panel-body'>" +
        //                     "<div class='img-div'><img src='" + item.productIcon + "'" + "class='img-circle'/></div>" +
        //                     "<div>价格：" + item.price +
        //                     "<div class='panel-footer'>" +
        //                     "<button type='button' style='text-align:center;' class='btn btn-primary '><a style='color:yellow' href='buyer/product/productInfoDetail/" + item.id + "'>查看详情</a></button>" +
        //                     " </div> " +
        //                     "</div>" +
        //
        //                     "</div>");
        //         })
        //     }
        // });
    })


</script>
</body>