<%--
  Created by IntelliJ IDEA.
  User: YQ
  Date: 2019/4/7
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Android招聘</title>

</head>
<link href="/CSS/core.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/JS/jquery-2.1.1.min.js"></script>
<style>
    .rightBody{
        background: powderblue;
    }
    .rightBody>p{
        font-size: 18px;
        color: red;

    }
    .detailResume_bottom{
        width: 100px;
        height: 30px;
        text-align: center;
    }
    .submit{
        margin-left: 250px;
        display: inline;
        width: 100px;
        height: 30px;
        background: red;
        border: none;
        cursor: pointer;
        color: white;
        font-size: 16px;
        font-weight: bold;
    }
    #inviteBtn{

    }
    #collectBtn{
        position: relative;
        left: 150px;
        top: -30px;

    }
    #invite,#collect{
        display: none;
        position: absolute;
        left: 400px;
        top: 200px;
        width: 400px;
        height: 300px;
        background: darkseagreen;
        opacity: 0.9;
        outline: 1px solid red;
    }
 #inviteSubmit,.cancel{
     border: none;
     background: red;
     width: 60px;
     height: 30px;
     color: white;
     font-size: 16px;
     cursor: pointer;
 }
</style>
<script>
    $(document).ready(function () {
        $("#inviteBtn").click(function () {
            $("#invite").show();
        })
        $("input[name='cancel']").click(function () {
            $(this).parent().hide();
        })
        $("#collectBtn").click(function () {
            $("#collect").submit();


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
        <p>基本信息</p>
        <div style="outline: 1px solid red;">
            姓名：<span>${detailedResume.resumeBean.userName}</span><br/>
            性别：
            <c:if test="${detailedResume.resumeBean.gender == '1'}">
                <span>男</span><br/>
            </c:if>
            <c:if test="${detailedResume.resumeBean.gender == '0'}">
                <span>女</span><br/>
            </c:if>
            年龄：<span>${detailedResume.resumeBean.age}</span><br/>
            城市：<span>${detailedResume.resumeBean.city}</span><br/>
            电话：<span>${detailedResume.resumeBean.tel}</span><br/>
            学历：<span>${detailedResume.resumeBean.qualifications}</span><br/>
            工作经验：<span>${detailedResume.resumeBean.work_year}年</span><br/>
            <c:if test="${detailedResume.resumeBean.state == 1}">
                求职状态：<span>目前正在找工作</span><br/>
            </c:if>
            <c:if test="${detailedResume.resumeBean.state == 0}">
                求职状态：<span>已找到工作</span><br/>
            </c:if>
        </div>
        <p>工作经验</p>
        <c:forEach var="experience" items="${detailedResume.experienceList}" varStatus="i">
            <div style="outline: 1px solid red;">
                    ${i.count}
                年份：${experience.yearsStart} - ${experience.yearsEnd}<br/>
                企业名称：${experience.companyName}<br/>
                就职岗位：${experience.job}<br/>
                岗位描述：${experience.jobDescribe}<br/>
            </div>
        </c:forEach>
        <p>教育经历</p>
        <c:forEach var="education" items="${detailedResume.educationList}" varStatus="i">
        年份：${education.yearsStart} - ${education.yearsEnd}
        学校：${education.school}
        专业：${education.major}<br/>
        </c:forEach>
        <p>求职意向</p>
        <div style="outline: 1px solid red;">
            意向工作：<span>${detailedResume.jobIntentBean.job}</span><br/>
            意向城市：<span>${detailedResume.jobIntentBean.city}</span><br/>
            意向薪资：<span>${detailedResume.jobIntentBean.intentSalary}</span><br/>
            到岗时间：<span>${detailedResume.jobIntentBean.arrivalTime}</span><br/>
            自我评价：<span>${detailedResume.jobIntentBean.selfEvaluation}</span><br/>
            标签：<span>${detailedResume.jobIntentBean.selfTag}</span><br/>
        </div>
        <p>项目经验</p>
        <div style="outline: 1px solid red;">
            <c:forEach var="exprience" items="${detailedResume.projectExprienceList}" varStatus="i">
                ${i.count}
                时间段：${exprience.timeStart} - ${exprience.timeEnd}<br/>
                企业名称：${exprience.companyName}<br/>
                项目名称：${exprience.projectName}<br/>
                项目描述：${exprience.proDescribe}<br/>
            </c:forEach>
        </div>
        <p>教育和工作情况</p>
        <div style="outline: 1px solid red;">
            <c:forEach var="eduJobCondition" items="${detailedResume.eduJobConditionList}" varStatus="i">
                ${i.count}
                时间：${eduJobCondition.times}
                荣誉：${eduJobCondition.conDescribe}<br/>
            </c:forEach>
        </div>
        <div class="detailResume_bottom">
            <input id="inviteBtn" class="submit" type="button" value="邀请">
            <input id="collectBtn" class="submit" type="button" value="收藏">
            <form id="invite" action="/detailedInfo.do/invitation">
                <input type="text" name="personId" value="${detailedResume.resumeBean.userId}" style="display: none">
                <input type="text" name="companyId" value="${sessionScope.userId}" style="display: none">
                所邀请的岗位：
                <select name="job">
                    <c:forEach var="job" items="${sessionScope.myJobs}" >
                        <option value="${job}">${job}</option>
                    </c:forEach>
                </select><br/>
                留言：
                <textarea name="msg" id="" cols="30" rows="10"></textarea><br/><br/><br/>
                <input type="text" name="resumeName" value="${detailedResume.resumeBean.resumeName}"  style="display: none">
                <input id="inviteSubmit"   type="submit" value="确定" >
                <input class="cancel" type="button" name="cancel" value="取消">
                <input type="text" style="display: none" name="statusPage" value="detailedResume.jsp">
            </form>
            <input id="inviteResult" style="display: none" type="button" value="${inviteResult}">
            <script>
                if($("#inviteResult").val() == "invite success"){
                    alert("邀请成功")
                }
            </script>
            <form id="collect" action="/collection/collectResume.do" >
                <input type="text" name="personId" value="${detailedResume.resumeBean.userId}">
                <input type="text" name="companyId" value="${sessionScope.userId}">
                <input type="text" name="resumeName" value="${detailedResume.resumeBean.resumeName}">
                <input type="text" style="display: none" name="statusPage" value="detailedResume.jsp">
            </form>
        </div>
    </div>
</div>
</body>
</html>

