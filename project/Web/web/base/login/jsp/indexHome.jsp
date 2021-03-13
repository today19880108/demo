<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="path" value="${pageContext.request.contextPath}" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>投资项目库-欢迎页</title>
<jsp:include page="../../../comm/jsp/includeJquery.jsp" flush="true"></jsp:include>
<jsp:include page="../../../comm/jsp/includeCommon.jsp" flush="true"></jsp:include>
<script type="text/javascript" src="${path}/web/comm/js/base64.js"></script>
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
</body>
</html>
