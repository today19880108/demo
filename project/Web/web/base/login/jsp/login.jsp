<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="path" value="${pageContext.request.contextPath}" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>投资项目库-登录页面</title>
<jsp:include page="../../../comm/jsp/includeJquery.jsp" flush="true"></jsp:include>
<jsp:include page="../../../comm/jsp/includeCommon.jsp" flush="true"></jsp:include>
<script type="text/javascript" src="${path}/web/comm/js/base64.js"></script>
<script type="text/javascript" src="${path}/web/base/login/js/login.js"></script>
<link rel="stylesheet" type="text/css" href="${path}/web/base/login/css/login.css"></link>
<script type="text/javascript">
var path = '${path}';
</script>
</head>
<body>
<div class="login_body_head">
	<div class="body_head_right">
		<div class="body_head_right_main"></div>
	</div>
</div>
<div class="login_body">
	<div class="center">
	<div class="right">
		<form id="user_form" method="post">
			<ul>
			<li>
				<div class="name">用户名：
				</div>
				<input type="text" name="USER_LOGIN" value="${sessionScope.user_temp.USER_LOGIN}" 
				<c:if test="${sessionScope.user_temp.loginError=='1'}">
					class="error"
				</c:if>
				/>
			</li>
			<li>
				<div class="name">密码：</div>
				<input type="password" name="USER_PWD"  
				<c:if test="${sessionScope.user_temp.loginError!='2'}">
					value="${sessionScope.user_temp.USER_PWD}"  
				</c:if>
				<c:if test="${sessionScope.user_temp.loginError=='2'}">
					class="error"
				</c:if>
				/>
				<div class="errorInfo" id="errorInfo">
				<c:if test="${sessionScope.user_temp.loginError=='1'}">
					用户名输入错误
				</c:if>
				<c:if test="${sessionScope.user_temp.loginError=='2'}">
					密码输入错误
				</c:if>
				</div>
			</li>
			</ul>
		</form>
		<div class="but">
			<button id="login" class="login">登 录</button>
			<button id="reset">重 置</button>
		</div>	
	</div>	
	</div>
</div>
</body>
</html>
<%
//清除用户临时信息
session.setAttribute("user_temp", null);
%>
