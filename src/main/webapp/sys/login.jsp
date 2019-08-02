<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<script type="text/javascript" src="/JS/jquery-2.1.1.min.js"></script>
<style>
    *{
        margin: 0px;
        padding: 0px;
    }
    body{
        width: 100%;
        height: 100%;
        outline: 1px red dashed;
        background-image: url("../images/loginImg.jpg");
        background-size: 100% 100%;
    }
    form{
        width: 400px;
        height: 250px;
        background: #3084C5;
        opacity: 0.8;
        position: relative;
        left: 480px;
        top: 60px;
        color: white;
        text-align: center;
        box-sizing: border-box;
        padding-top: 50px;
        line-height: 35px;

    }
    form input{
        outline: none;
        border: none;
        width: 220px;
        height: 25px;
        font-family: 宋体;
        font-size: 14px;
        font-weight: bold;
    }
    .title{
        font-size: 24px;
        color: white;
        text-align: center;
        position: relative;
        top: 30px;
    }
    #ajaxBtn{
        width:100px;
        height: 30px;
        text-align: center;
        margin-top: 20px;
        background: lawngreen;
        cursor: pointer ;
    }
</style>
<script>
    $(document).ready(function () {
        $("#ajaxBtn").click( function () {
            if ($(".userName").val() == null || $(".userName").val() == "" || $(".password").val() == null || $(".password").val() == "") {
                alert("用户名或密码为空！");
                return false;
            }
        })
    })
</script>
<body>
    <p class="title">Android招聘系统企业登录</p>
    <form action="/android_recruit/login.do" method="get">
        <input class="status" type="button" style="display: none" value="${status}">
        <input class="registerStatus" type="button" style="display: none;" value="${registerStatus}">
        用户名：<input class="userName" type="text" name="userName"><br>
        密  &nbsp;&nbsp;码：<input class="password" type="password" name="password"><br>
        <input  type="submit" id="ajaxBtn" value="登录"><br/>
        <a style="color: white" href="/sys/register.jsp">没有账号？去注册</a>
    </form>
<script>
    if ($(".status").val() !=null && $(".status").val() != ""){
        alert($(".status").val());
        $(".status").val("");
    }
    if ($(".registerStatus").val()!= null && $(".registerStatus").val() != ""){
        alert("注册成功，去登录吧");
        $(".registerStatus").val("");
    }
</script>
</body>

</html>
