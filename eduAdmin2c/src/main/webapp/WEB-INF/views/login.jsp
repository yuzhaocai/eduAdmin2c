<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% 
	String path = request.getContextPath();     
	String basePath =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
<link rel="stylesheet" href="<%=basePath%>/static/styles/base.css" type="text/css"/>
<link rel="stylesheet" href="<%=basePath%>/static/styles/login.css" type="text/css"/>
<script type="text/javascript" src="<%=basePath%>/static/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/scripts/login.js"></script>
<script type="text/javascript">
if (window != top) 
	top.location.href = location.href; 
</script>
</head>
<body>
	<div class="wrapper">
		<div class="header"></div>
		<div class="content">
			<div class="login-box">
				<h1>Class8后台管理系统</h1>
				<form action="<%=basePath%>/login" method="post">
					<p>
						<input type="text" class="input username" name="username" placeholder="请输入用户名或邮箱" value="${username}">
					</p>
					<p>
						<input type="password" class="input password" name="password"  placeholder="请输入密码">
					</p>
					<p>
						<input type="text" class="input captacha" name="captacha"  placeholder="请输入验证码">
						<a href="javascript:void(0);" style="float: right;margin: 10px 0 0 10px;" onclick="changeCaptacha();">换一张</a>
						<img id="captachaImage" alt="验证码" src="<%=basePath%>/captacha" style="display: block;float: right;vertical-align: middle;" onclick="changeCaptacha();">
					</p>
					<c:if test="${empty message}">
					  	<p class="invalid">
					  		<i></i><span class="message"></span>
					  	</p>
				  	</c:if>
				  	<c:if test="${not empty message}">
					  	<p class="invalid" style="display: block;">
					  		<i></i>
					  		<span class="message">${message}</span>
					  	</p>
				  	</c:if>
					<div>
						<p>
							<span style="float: left;">
								<a href="#">忘记密码?</a>
							</span> 
				           	<span style="float: right;">
				           		<a style="margin-right: 10px;" href="#">注册</a>
				           		<input type="button" class="logging" value="登录">
				           	</span>   
						</p>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>