<%@page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Android招聘</title>

</head>
<link href="/CSS/core.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/JS/jquery-2.1.1.min.js"></script>
<style>
    .info{
        width:400px;
        height:200px;
        font-size: 16px;
        outline: none;
    }
    .rightBody{
        box-sizing: border-box;
        padding-left: 100px;
    }
    .rightBody span{
        font-size: 18px;

    }
    .rightBody input, .property{
        outline: none;
        width: 300px;
        height: 30px;

    }

    #subBtn{
        width: 100px;
        margin-left: 200px;
        background: red;
        color: white;
        font-size: 16px;
        outline: none;
        border: none;
        cursor: pointer;
    }
    #tips{
        display: none;
        width: 200px;
        height: 100px;
        position: absolute;
        left: 600px;
        top: 200px;
        z-index: 10;
        background: greenyellow;
        text-align: center;
        line-height: 100px;

    }
    .menu_two{
        background: darkseagreen !important;
    }
</style>
<script>
    $(document).ready(function () {
        if($("#resultState").val() == 'save success'){
            $("#tips").show(300,function () {
                window.setTimeout(function () {
                    $("#tips").hide(300)
                },1000)
            })
        }
      $("#subBtn").click(function () {
          if ($("input[name='tel']").val().length >11){
              alert("请输入正确的电话号码");
              return false;
          }
          $("#form").submit();
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
        <form id="form" action="/Enterprise/updateCompany.do" METHOD="get">
            <br/><span>名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称</span>：<input type="text" name="name" value="${enterprise.e_name}"><br/><br/>
            <span>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;质</span>：
            <%--<input type="text" name="property" value="${enterprise.e_property}"><br/><br/>--%>
            <select class="property" name="property">
                <c:if test="${enterprise.e_property == '民营企业'}">
                    <option value="">-请选择-</option>
                    <option value="${enterprise.e_property}" selected>民营企业</option>
                    <option value="国营企业">国营企业</option>
                </c:if>
                <c:if test="${enterprise.e_property == '国营企业'}">
                    <option value="">-请选择-</option>
                    <option value="民营企业">民营企业</option>
                    <option value="${enterprise.e_property}" selected>国营企业</option>
                </c:if>
                <c:if test="${enterprise.e_property != '民营企业' && enterprise.e_property != '国营企业'}">
                    <option value="">-请选择-</option>
                    <option value="民营企业">民营企业</option>
                    <option value="国营企业">国营企业</option>
                </c:if>
            </select><br/><br/>
            <span>规&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;模</span>：<input type="text" name="scale" value="${enterprise.e_scale}">人<br/><br/>
            <span>城&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市</span>：<input type="text" name="city" value="${enterprise.e_city}"><br/><br/>
            <span>地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址</span>：<input type="text" name="addr" value="${enterprise.e_addr}"><br/><br/>
            <span>电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话</span>：<input type="text" name="tel" value="${enterprise.e_tel}"><br/><br/>
            <span>企业概述</span>：<textarea class="info" type="text" name="info" >${enterprise.e_info}</textarea><br/><br/>
            <input id="subBtn" type="submit" value="保存" >
        </form>
        <div id="tips">提交称成功</div>
        <input id="resultState" type="text" value="${saveEnterpriseStatus}" style="display: none" >
    </div>
</div>
</body>
</html>
