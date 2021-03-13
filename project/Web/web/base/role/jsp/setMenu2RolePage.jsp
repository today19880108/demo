<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<c:set var="path" value="${pageContext.request.contextPath}" />

<input type="hidden" name="ROLE_ID" id="ROLE_ID" value="${param.ID}" />
<div class="dia_containt">	
	<div class="border">
		<ul id="tree" class="ztree"></ul>
	</div>
</div>

<script type="text/javascript" src="${path}/web/base/role/js/setMenu2RolePage.js"></script>
<link rel="stylesheet" type="text/css" href="${path}/web/base/role/css/setMenu2RolePage.css" />