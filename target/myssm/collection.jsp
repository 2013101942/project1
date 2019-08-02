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
    .menu_seven{
        background: darkseagreen !important;
    }
</style>
<script>
    $(document).ready(function () {
        $(".divResume").click(function () {
            $(this).submit();
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
        <c:forEach var="divResume" items="${sessionScope.collectResume}" varStatus="i">
            <form class="divResume" action="/detailedInfo.do/resume" method="get">
                <span class="job">${divResume.job}</span><br/>
                <span class="name">${divResume.name}</span>&nbsp;&nbsp;&nbsp;
                <c:if test="${divResume.gender == 1}">
                    <span>男</span>
                </c:if>
                <c:if test="${divResume.gender == 0}">
                    <span>女</span>
                </c:if>&nbsp;&nbsp;&nbsp;
                <span>${divResume.age}</span>&nbsp;&nbsp;&nbsp;
                <span>${divResume.qualifications}</span>
                <span  class="major">专业：${divResume.major}</span>
                <input type="text" style="display: none" name="userId" value="${divResume.userId}">
                <input type="text" style="display: none" name="resumeName" value="${divResume.resumeName}">
                <input type="text" style="display: none" name="statusPage" value="collection.jsp">
            </form>
        </c:forEach>
        <input id="cancelCollectResult" type="text" style="display: none" name="cancelCollectResult" value="${cancelCollectResult}">
        <script>
            if ($("#cancelCollectResult").val() == "cancelCollect success") {
                alert("已取消收藏")
            }
        </script>
    </div>
</div>
</body>
</html>

