<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>首页</title>
        <style>
            .form-control{
                border:1px solid #CCC;
                border-radius:3px;
                width:200px;
                height:24px;
                line-height:24px;
                font-size:16px
            }
            .btn{
                border:1px solid #CCC;
                border-radius:4px;
                color:#333;
                background-color:#FFF;
                padding:6px 12px;
                font-weight:400;
                font-size:14px;
            }
        </style>
    </head>
    <body>
        <div style="text-align:center;margin-top:50px">
            <form action="/login.shtml" method="POST">
                <label>用户名：</label><input class="form-control" type="text" name="username" /><br/><br/>
                <label>密&nbsp;码：</label><input class="form-control" type="password" name="password" /><br/><br/>
                <button type="submit" class="btn">登录</button>
            </form>
        </div>
    </body>
</html>