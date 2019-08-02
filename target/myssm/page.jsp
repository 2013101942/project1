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
    .menu_one{
        background: darkseagreen !important;
    }
    #search{
        width: 100%;
        height: 40px;
        line-height: 40px;
        box-sizing: border-box;
        padding-left: 20px;
        background: ghostwhite;
        opacity: 0.9;
    }
    .collect{
        width: 70px;
        height: 30px;
        background: green;
        position: absolute;
        left: 620px;
        bottom: 3px;
        color: white;
        font-size: 18px;
        border: none;
        outline: none;
        border-radius: 3px 3px 3px 3px;
        line-height: 30px;
        cursor: pointer;
    }

    #submit{
        border: none;
        outline: none;
        width: 60px;
        height: 25px;
        line-height: 25px;
        background: mediumseagreen;
        color: white;
        font-size: 16px;
        cursor: pointer;
        border-radius: 3px 3px 3px 3px;
    }
    #bottom{
        font-size: 14px;
        margin-left: 180px;
        margin-top: 10px;
    }
    .bottom_key{
        font-size: 16px;
        color: red;
        margin-left: 5px;
        margin-right: 5px;
    }
</style>
<script>
    $(document).ready(function () {

        $("#next").click(function () {
            $("input[name='pageNum']").val(${page.pageNum}+1);
            $("#submit").click();
        })
        $("#pre").click(function () {
            $("input[name='pageNum']").val(${page.pageNum}-1);
            $("#submit").click();
        })
        $(".divResume").click(function () {
            $(this).submit();
        })
        $(".collect").click(function () {
            $(this).parent().attr("action","/collection/collectResume.do");
            $(this).parent().submit();
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
        <form id="search" action="/android_recruit/page.do" method="get">
            岗位：
            <select name="job">
                    <option selected value="">-请选择-</option>
                <c:forEach var="job" items="${page.jobs}">
                    <c:if test="${page.bean.job == job}">
                        <option value="${job}" selected>${job}</option>
                    </c:if>
                    <c:if test="${page.bean.job != job}">
                        <option value="${job}">${job}</option>
                    </c:if>
                </c:forEach>
            </select>
            &nbsp;&nbsp;
            性别：
            男<input name="gender" type="radio" value="1" ${page.bean.gender == "1"? "checked":"" }>
            女<input name="gender" type="radio" value="0" ${page.bean.gender == "0"? "checked":"" }>
            &nbsp;&nbsp;
            学历：
            <select name="qualifications">
                    <option selected value="">-请选择-</option>
                <c:forEach var="qualifications" items="${page.qualifications}">
                    <c:if test="${page.bean.qualifications == qualifications}">
                        <option value="${qualifications}" selected>${qualifications}</option>
                    </c:if>
                    <c:if test="${page.bean.qualifications != qualifications}">
                        <option value="${qualifications}">${qualifications}</option>
                    </c:if>
                </c:forEach>
            </select>
            &nbsp;&nbsp;
            每页显示：
            <select name="pageRecord">
                <c:forEach var="i" begin="1" end="10">
                    <c:if test="${page.pageRecord == i}">
                        <option value="${i}" selected>${i}</option>
                    </c:if>
                    <c:if test="${page.pageRecord != i}">
                        <option value="${i}">${i}</option>
                    </c:if>
                </c:forEach>
            </select>
            <input type="text" style="display: none" name="pageNum" value="${page.pageNum}">
            &nbsp;&nbsp;
            <input id="submit" type="submit" value="查询">
        </form>
        <c:forEach var="divResume" varStatus="i" items="${sessionScope.page.divBeans}">
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
                <input type="text" style="display: none;" name="userId" value="${divResume.userId}">
                <input type="text" style="display: none;" name="resumeName" value="${divResume.resumeName}">
                <input type="text" style="display: none" name="personId" value="${divResume.userId}">
                <input type="text" style="display: none" name="companyId" value="${sessionScope.userId}">
                <input type="text" style="display: none" name="statusPage" value="page.jsp">
                <input class="collect" type="button" value="收藏"/>

            </form>
        </c:forEach>
        <%--结果码--%>
        <input id="collectResult" type="text" style="display: none" name="collectResult" value="${collectResult}">
        <script>
            if ($("#collectResult").val() == "collect success"){
                alert("收藏成功");
                $("#collectResult").val("");
            }
        </script>
        <div id="bottom">
            一共有<span class="bottom_key">${page.recordTotal}</span>条记录
            一共有<span class="bottom_key">${page.pageCount}</span>页
            当前是第<span class="bottom_key">${page.pageNum}</span>页
            <c:if test="${page.pageNum >= 2}">
                <button id="pre"  >上一页</button>
            </c:if>
            <%--<input type="button" name="next" value="下一页">--%>
            <c:if test="${page.pageNum < page.pageCount}">
                <button id="next"  >下一页</button>
            </c:if>

        </div>
    </div>
</div>
</body>
</html>
