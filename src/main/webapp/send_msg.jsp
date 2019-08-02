<%@page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Android招聘</title>

</head>
<link href="/CSS/core.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/JS/jquery-2.1.1.min.js"></script>
<style>
    .msgDiv{
        text-indent: 20px;
        width: 100%;
        height: 60px;
        box-sizing: border-box;
        margin-top: 2px;
        padding-left: 50px;
        line-height: 30px;
        cursor: pointer;
        background: lavender;
        border-radius: 5px 5px 5px 5px;
        border: gray 1px solid;
    }
    .msgDiv .msgCompanyName{
        font-size: 18px;
        font-weight: bold;
        color: red;

    }
    .msgDiv .msgCont{
        margin-left: 40px;
    }
    .msgDiv .times{
        margin-left: 100px;
    }
    .content{
        display: none;
        position: absolute;
        left: 500px;
        top: 100px;
        width: 400px;
        height: 300px;
        z-index: 10;
        background: greenyellow;
        outline: 1px solid gray;
    }
    .content textarea{
        width: 100%;
        height: 80%;
        font-size: 18px;
        position: relative;
        left: -20px;
        outline: none;
    }
    .cancelContent{
        border: none;
        background: red;
        width: 60px;
        height: 30px;
        font-size: 16px;
        color: white;
        line-height: 30px;
        margin-left: 300px;
        margin-top: 10px;
        cursor: pointer;
    }
    .menu_six{
        background: darkseagreen !important;
    }
</style>
<script>
    $(document).ready(function () {
        $(".msgDiv").click(function () {
            $(this).find(".content").fadeIn();

        })
        $(".cancelContent").click(function () {
            $(".content").fadeOut();
            return false;
        })
    })
</script>
<body>
<div id="wrap">
    <div id="title">
        Android招聘系统
        <div class="companyName">
            <span>企业名称：</span>
            <span id="companyNameId">${sessionScope.companyName}</span>
        </div>
        <div class="goLoginAndGoRegister">
            <a href="/sys/login.jsp" >登录</a>/
            <a  href="/sys/register.jsp">注册</a>
        </div>
    </div>
    <div class="leftMenu">
        <a class="menu_one" href="/android_recruit/page.do">首页</a>
        <a class="menu_two" href="/Enterprise/checkCompany.do">编辑企业信息</a>
        <a class="menu_three" href="/releaseRecruit.jsp">发布招聘信息</a>
        <a class="menu_four" href="/Resume.do/receivedResumeList">筛选简历</a>
        <a class="menu_night" href="/Resume.do/checkSuccessResume">已通过的简历</a>
        <a class="menu_five" href="/message.do/pc_received">查看来信</a>
        <a class="menu_six" href="/message.do/pc_sended">已留言</a>
        <a class="menu_seven" href="/collection/collectResumeList">我的收藏</a>
        <a class="menu_eight" href="/Recruit/checkMyRecruitInfo.do">我的招聘信息</a>
    </div>
    <div class="rightBody">
        <c:forEach var="msg" items="${msgs}" varStatus="i">
            <div class="msgDiv">
                <span class="msgCompanyName">${msg.userName}</span>
                <span class="times">${msg.times}</span><br/>
                <span class="msgCont">${msg.message}</span>
                <div class="content">
                    <textarea >${msg.message}</textarea><br/>
                    <button class="cancelContent">确定</button>
                </div>
            </div>

        </c:forEach>

    </div>
</div>
</body>
</html>
