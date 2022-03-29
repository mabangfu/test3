<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>首页</title>
  <!-- Bootstrap -->
  <link href="./css/bootstrap.min.css" rel="stylesheet"/>
</head>
<script src="./js/jquery-3.6.0.min.js"></script>
<script src="./js/bootstrap.min.js"></script>
  <body>
  <div align="center">
  	<a
            href="${path}/findAllUserByPage?username=${condition.username[0]}&address=${condition.address[0]}&current=1&size=10" style="text-decoration:none;font-size:33px">查询所有用户信息
	</a>
  </div>
  </body>
</html>