<!DOCTYPE html>
<html>
<title>
   404 NOT FOUND !
</title>
<style type="text/css">
    .box{
        text-align: center;
        display: -webkit-flex;
        display: flex;
        width:100%;
        flex-direction: column;
    }
</style>
<body>
    <div class="box">
        <div class="innerbox">
            <h1>404 NOT FOUND</h1>
            <h2>${errorMsg!""}</h2>

            <h3><a href="${url!"/"}">3秒后跳主回页</a></h3>
        </div>
    </div>
</body>
</html>
<script type="text/css">
    setTimeout('location.href="${url}"', 3000);

</script>