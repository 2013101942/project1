<%--
  Created by IntelliJ IDEA.
  User: YQ
  Date: 2019/4/2
  Time: 23:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>upload</title>
</head>
<body>
    <form action="/upload" method="post" enctype="multipart/form-data">
        文件：<input type="file" name="files">
        文件：<input type="file" name="files">
              <input type="text" name="username">
        <input type="submit">
    </form>

</body>
</html>
