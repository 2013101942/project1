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
    .divRecruit{
        display: block;
        width: 100%;
        border: 1px solid gray;
        box-sizing: border-box;
        padding-left: 20px;
        text-indent: 20px;
        border-radius: 5px 5px 5px 5px;
        margin-top: 2px;
        background: #efefef;
        position: relative;
    }
    .detailedRecruit{
        box-sizing: border-box;
        padding-left: 20px;
    }
    .detailedRecruit input{
        width: 200px;
        height: 30px;
        font-size: 16px;
        outline: none;
    }
    .detailedRecruit textarea{
        width: 400px;
        height: 150px;
        font-size: 16px;
    }

    .edit ,.delete{
        width: 80px;
        height: 30px;
        border: none;
        outline: none;
        background: red;
        font-size: 18px;
        color: white;
        margin-bottom: 3px;
        cursor: pointer;
    }
    .edit{
        margin-left: 200px;
    }

    .job{
        margin-left: 10px;
    }
    .times{
        position: absolute;
        left: 280px;

    }
    .salary,.staffNum{
        color: red;
    }
    .detailedRecruit{
        display: none;
        width: 600px;
        height: 600px;
        position: absolute;
        left: 400px;
        top: 30px;
        z-index: 10;
        background: lightgreen;
        text-indent: 0px !important;
    }
    .subRecruit{
        border: none;
        outline: none;
        width: 80px !important;
        height: 30px;
        background: red;
        color: white;
        font-size: 16px;
        margin-left: 230px;
    }
    .tips{
        display: none;
        position: absolute;
        left: 550px;
        top: 200px;
        width: 300px;
        height: 150px;
        background: lightgreen;
        font-size: 18px;
        text-align: center;
        line-height: 150px;
    }
    .menu_eight{
        background: darkseagreen !important;
    }
</style>
<script>
    $(document).ready(function () {
        //判断状态
        if ($(".state").val() == 'delete success'){
            $(".tips").html("删除成功");
            $(".tips").show(300,function () {
                window.setTimeout(function () {
                    $(".tips").hide(300);
                },2000)
            });
        } else if ($(".state").val() == 'update success'){
            $(".tips").html("修改成功");
            $(".tips").show(300,function () {
                window.setTimeout(function () {
                    $(".tips").hide(300);
                },2000)
            });
        }
        $(".edit").click(function () {
            $(".detailedRecruit").show(300);
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
        <c:forEach var="divRecruit" varStatus="i" items="${sessionScope.myRecruitInfo}">
            <form class="divRecruit" action="/Recruit/deleteMyRecruitInfo.do" method="get">
                <input type="text" name="recruitId" value="${divRecruit.recruitId}" style="display: none;">
                <span class="job">${divRecruit.job}</span>&nbsp;&nbsp;&nbsp;
                <span class="times">${divRecruit.times}</span><br/>
                招收<span class="staffNum">${divRecruit.staffNum}</span>人&nbsp;&nbsp;&nbsp;
                <span class="salary">${divRecruit.salaryStart}</span>/月-
                <span class="salary">${divRecruit.salaryEnd}</span>/月
                <input class="edit" type="button" value="编辑"/>&nbsp;&nbsp;&nbsp;
                <input class="delete" type="submit" value="删除"/>
            </form>
            <form class="detailedRecruit" action="/Recruit/updateMyRecruitInfo.do">
                <input type="text" name="recruitId" value="${divRecruit.recruitId}" style="display: none;width: 100px; height: 20px">
                <input type="text" style="display: none" name="userId" value="${sessionScope.userId}">
                <input class="state" type="text" value="${state}" style="display: none;">
                <br/>岗&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位：
                <input type="text" name="job" value="${divRecruit.job}"><br/><br/>
                需&nbsp;求&nbsp;人&nbsp;数：
                <input type="text" name="staffNum" value="${divRecruit.staffNum}">人<br/><br/>
                薪&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;资：
                <input type="text" name="salaryStart" value="${divRecruit.salaryStart}">/月-
                <input type="text" name="salaryEnd" value="${divRecruit.salaryEnd}">/月<br/><br/>
                招&nbsp;聘&nbsp;简&nbsp;章：
                <textarea class="recruitInfo" name="recruitInfo" >${divRecruit.recruitInfo}</textarea><br/><br/>
                经验及要求：
                <textarea class="experience" name="experience">${divRecruit.experience}</textarea><br/><br/>
                <input class="subRecruit" type="submit" value="确定">
            </form>
        </c:forEach>
        <div class="tips"></div>
    </div>


</div>
</body>
</html>
