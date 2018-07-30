
<nav class="navbar navbar-inverse" role="navigation">
    <div class="container=fluid">
        <h1>嘿，欢迎来到点餐系统</h1>
        <div class="nav-header">
            <a class="nav-brand" href="#">Home</a>

        </div>

        <ul class="nav navbar-nav">
         <#assign username="">
        <#if username == "unknown ">
            <li class="active"><a href="/customer/login">请登录</a></li>
        </#if>
         <#if  username!="unknown">
             <li><a href="#">欢迎${username}登录</a></li>
         </#if>
        </ul>
        <form class="navbar-form navbar-left" role="search">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Search">
            </div>
            <button type="submit" class="btn btn-default">提交</button>
        </form>
    </div>
</nav>
