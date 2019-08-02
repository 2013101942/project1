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
    #recruit{
        box-sizing: border-box;
        padding-left: 80px;

    }
    #recruit input{
        width: 200px;
        height: 30px;
        font-size: 14px;
        outline: none;
    }
    #recruit textarea{
        width: 300px;
        height: 150px;
        outline: none;
        font-size: 18px;
    }
    #recruit span{
        color: red;
        font-size: 16px;
        font-weight: bold;
    }
    #subRecruit{
        border: none;
        color: white;
        height: 40px !important;
        font-size: 18px !important;
        background: red;
        margin-top: 30px;
        margin-left: 120px;
        cursor: pointer;
    }
    #tips{
        display: none;
        position: absolute;
        left: 550px;
        top: 200px;
        width: 300px;
        height: 100px;
        background: darkseagreen;
        text-align: center;
        line-height: 100px;
        outline: red 1px solid;
    }
    .menu_three{
        background: darkseagreen !important;
    }
</style>
<script>
    $(document).ready(function () {
        $("#subRecruit").click(function () {

            if ( $("input[name='job']").val() == null || $("input[name='job']").val() ==""){
                $("#tips").html("岗位不能为空");
                $("#tips").fadeIn(300);
                var timeout = window.setTimeout(function () {
                    $("#tips").fadeOut(300);
                },3000);
                timeout.clear;
                return false;
            }else if ($("input[name = 'staffNum']").val() == null || $("input[name = 'staffNum']").val() ==""){
                $("#tips").html("需求人数不能为空")
                $("#tips").fadeIn(300);
                var timeout = window.setTimeout(function () {
                    $("#tips").fadeOut(300);
                },3000);
                return false;
            } else if ($("input[name = 'salaryStart']").val() == null || $("input[name = 'salaryStart']").val()=="" || $("input[name = 'salaryEnd']").val() == null || $("input[name = 'salaryEnd']").val()==""){
                $("#tips").html("薪水不能为空")
                $("#tips").fadeIn(300);
                var timeout = window.setTimeout(function () {
                    $("#tips").fadeOut(300);
                },3000);
                return false;
            } else if($("textarea[name='recruitInfo']").val() == null || $("textarea[name='recruitInfo']").val()==""){
                $("#tips").html("招聘简章不能为空")
                $("#tips").fadeIn(300);
                var timeout = window.setTimeout(function () {
                    $("#tips").fadeOut(300);
                },3000);
                return false;
            }
            $("#recruit").submit();
        })
        if ($("#releaseResult").val() == "releaseRecruit success"){
            $("#tips").html("发布成功")
            $("#tips").fadeIn(300);
            var timeout = window.setTimeout(function () {
                $("#tips").fadeOut(300);
            },3000);
            $("#releaseResult").val("")
            $("#tips").html("")
        }
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
        <form id="recruit" action="/Recruit/releaseRecruit.do">
            <input type="text" name="companyId" value="${sessionScope.userId}" style="display: none">
            <span>招聘岗位</span>：<input type="text" name="job"><br/><br/>
            <span>需求人数:</span> &nbsp;<input type="text" name="staffNum"><br/><br/>
            <span>月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;薪</span>：<input type="text" name="salaryStart">/月 - <input type="text" name="salaryEnd">/月<br/><br/>
            <span>招聘简章</span>：<textarea type="text" name="recruitInfo"></textarea><br/><br/>
            <span>招聘要求</span>：<textarea type="text" name="experience"></textarea><br/><br/>
            <input id="subRecruit" type="submit" value="发布">
        </form>
    </div>
    <input id="releaseResult" type="text" style="display: none;" value="${requestScope.releaseResult}">

    <div id="tips"></div>
</div>
</body>
</html>

