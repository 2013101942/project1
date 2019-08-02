
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<script type="text/javascript" src="/JS/jquery-2.1.1.min.js"></script>
<style>
    *{
        margin: 0;
        padding: 0;
    }
    body{
        width: 100%;
        height: 100%;
        outline: 1px red dashed;
        background-image:url("../images/register.gif");
        background-size: 100% 100%;
    }
    form{
        width: 400px;
        height: 300px;
        background: #9FB7A3;
        position: relative;
        left: 480px;
        top: 60px;
        opacity: 0.8;
        text-align: center ;
        line-height: 35px;
        box-sizing: border-box;
        padding-top: 30px;
    }
    form input{
        outline: none;
        border: none;
        width: 220px;
        height: 25px;
        font-size: 14px;
        font-family: 宋体;
        font-weight: bold;
    }
    #register{
        outline: none;
        border: none;
        background: red;
        color: white;
        font-size: 18px;
        height: 30px;
        margin-top: 30px;
        cursor: pointer;
    }
    .formTitle{
        font-size: 20px;
        font-weight: bold;
    }
</style>
<script>
    $(document).ready(function () {

        $("#register").click(function () {
            if ($(".userName").val() == null || $(".userName").val() == "" || $(".password").val() == null || $(".password").val() == "") {
                alert("用户名或密码为空！");
                return false;
            }
            if ($(".confirmPwd").val() == null || $(".confirmPwd").val() == ""){
                alert("请再次填写密码!");
                return false;
            }
            if ($(".password").val() != $(".confirmPwd").val()) {
                alert("两次密码不一致！");
                return false;
            }

        })
    })
</script>
<body>
    <form action="/register/registerPC.do" >
        <p class="formTitle">企业注册</p>
        用  &nbsp;户  &nbsp;名：<input class="userName" name="userName" type="text" placeholder="用户名"><br/>
        密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：<input class="password" name="password" type="password" placeholder="密码"><br/>
        确认密码：<input class="confirmPwd" type="password" placeholder="确认密码"><br/>
        <input id="register" type="submit" value="注册"><br/>
        <a href="/sys/login.jsp">已有账号？去登录</a>
    </form>
</body>
</html>
