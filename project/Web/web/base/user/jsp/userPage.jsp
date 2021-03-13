<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="path" value="${pageContext.request.contextPath}" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>用户页面</title>
<jsp:include page="../../../comm/jsp/includeJquery.jsp" flush="true"></jsp:include>
<jsp:include page="../../../comm/jsp/includeJqGrid.jsp" flush="true"></jsp:include>
<jsp:include page="../../../comm/jsp/includeZtree.jsp" flush="true"></jsp:include>
<jsp:include page="../../../comm/jsp/includeCommon.jsp" flush="true"></jsp:include>
<script type="text/javascript" src="${path}/web/base/user/js/userPage.js"></script>
<link rel="stylesheet" type="text/css" href="${path}/web/base/user/css/userPage.css" />
<script type="text/javascript">
var path = '${path}';
</script>
</head>
<body>
	<div class="containt">	
		<div class="border">
			<ul class="select">
				<li>
					<span>用户名称：</span>
					<input type="text" name="USER_NAME" id="USER_NAME"/>
				</li>
				<li>
					<span>所属部门：</span>
					<input type="text" name="DEPT_NAME" id="DEPT_NAME"/>
					<input type="hidden" name="DEPT" id="DEPT"/>
				</li>
				<li>
					<span>身份证号：</span>
					<input type="text" name="USER_CODE" id="USER_CODE"/>
				</li>
				<li>
					<span>联系电话：</span>
					<input type="text" name="TEL" id="TEL"/>
				</li>
				<li>
					<span>状态：</span>
					<select name="ISUSER" id="ISUSER">
						<option value="">---请选择---</option>
						<c:forEach var="varO" items="${ISUSERList}">
						<option value="${varO.ID}">${varO.NAME}</option>
						</c:forEach>
					</select>				
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