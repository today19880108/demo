<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="path" value="${pageContext.request.contextPath}" />
<c:redirect url="/base/index.do" context="${path}"/>  

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页跳转</title>
<link rel="shortcut icon" type="image/x-icon" href="${path}/web/comm/image/favicon.png" />
</head>
<body>
</body>
</html>

