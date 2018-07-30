<#include "./common/header.ftl">
<html>
<head>
    <script src="https://v3.bootcss.com/assets/js/ie-emulation-modes-warning.js"></script>
    <script src="https://v3.bootcss.com/assets/js/ie10-viewport-bug-workaround.js"></script>

    <link href="https://v3.bootcss.com/examples/signin/signin.css" rel="stylesheet">
    <link href="css/signin.css" type="text/css" rel="stylesheet">
</head>
<body>
<div class="container">
    <form class="form-signin">
        <h2 class="form-signin-heading" >请登录</h2>
        <label for="username" class="sr-only">管理员名字</label>
        <input type="text" name="username" class="form-control" placeholder="用户名" required-autofocus/>
        <label for="password"class="sr-only">密码</label>
        <input type="password" id="password" class="form-control" placeholder="密码"/>
        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember me">记住我
            </label>
        </div>
        <button value="登陆" type="submit" class="btn btn-lg btn-primary btn-block">登陆</button>


    </form>
</div> <!-- /container -->
</body>
</html>