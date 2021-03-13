<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="path" value="${pageContext.request.contextPath}" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>菜单页面</title>
<jsp:include page="../../../comm/jsp/includeJquery.jsp" flush="true"></jsp:include>
<jsp:include page="../../../comm/jsp/includeJqGrid.jsp" flush="true"></jsp:include>
<jsp:include page="../../../comm/jsp/includeZtree.jsp" flush="true"></jsp:include>
<jsp:include page="../../../comm/jsp/includeCommon.jsp" flush="true"></jsp:include>
<script type="text/javascript" src="${path}/web/base/menu/js/menuPage.js"></script>
<link rel="stylesheet" type="text/css" href="${path}/web/base/menu/css/menuPage.css" />
<script type="text/javascript">
var path = '${path}';
</script>
</head>
<body>
	<div class="containt">	
		<div class="border">
			<ul class="select">
				<li>
					<span>上级菜单：</span>
					<input type="text" name="PID_NAME" id="PID_NAME"/>
					<input type="hidden" name="PID" id="PID"/>
				</li>
				<li>
					<span>菜单名称：</span>
					<input type="text" name="NAME" id="NAME"/>
				</li>
				<li>
					<span>菜单路径：</span>
					<input type="text" name="PATH" id="PATH"/>
				</li>
			</ul>
		</div>
		<div class="border">
			<input type="button" value="新增" class="button" id="add"/>
			<input type="button" value="修改" class="button" id="upd"/>
			<input type="button" value="删除" class="button" id="del"/>
			
			<input type="button" value="查询" class="button search" id="search"/>
		</div>
		<div>
			<table id="grid"></table>
			<div id="page"></div>
		</div>
	</div>
</body>
</html>