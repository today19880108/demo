<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="path" value="${pageContext.request.contextPath}" />

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户超时</title>
<script type="text/javascript">
	function resendUrl(url){
		var winObj = window;
		while(winObj!=winObj.parent){
			winObj = winObj.parent;
		}
		winObj.location.href = url;
	}
</script>
</head>
<body>
	用户超时，请<a href="javascript:;" onclick="resendUrl('${path}/welcome/login.do')">重新登录</a>！
</body>
</html>