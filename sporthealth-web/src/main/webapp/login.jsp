<%--
  Created by IntelliJ IDEA.
  User: kongdingchao
  Date: 2019/1/19
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="WEB-INF/jsp/common/tag.jsp"%>
<html>
<head>
    <title>用户登陆</title>
    <%@include file="WEB-INF/jsp/common/head.jsp"%>
    <script type="application/javascript">

    </script>
</head>
<body>
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading text-center">
            <h2>用户登录</h2>
        </div>
        <div class="panel-body">
            <form action="subLogin" method="post" class="center-pill">
                用户名：<input id="user" type="text" name="user">
                密码：<input id="password" type="password" name="password">
                <input type="submit" value="登陆">
            </form>
        </div>
    </div>
</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>

</body>
</html>
