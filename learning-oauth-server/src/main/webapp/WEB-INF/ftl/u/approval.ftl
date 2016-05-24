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
    <div>你要授权${authorizationRequest.clientId}访问你的受保护资源吗？</div>
    <form action="/server/oauth/authorize" method="post">
        <input type="hidden" name="user_oauth_approval" value="true" />
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <input type="submit" value="授权"/>
    </form>
    <form action="/server/oauth/authorize" method="post">
        <input type="hidden" name="user_oauth_approval" value="false" />
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <input type="submit" value="拒绝"/>
    </form>
</div>
</body>
</html>