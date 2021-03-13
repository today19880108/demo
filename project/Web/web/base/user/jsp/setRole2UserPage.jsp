<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<c:set var="path" value="${pageContext.request.contextPath}" />

<input type="hidden" name="USER_ID" id="USER_ID" value="${param.USER_ID}"/>
<div class="dia_containt">	
	<div>
		<table id="grid2"></table>
		<div id="page2"></div>
	</div>
	<div class="border addtop">
		<button class="button" id="add2">添加</button>
		<button class="button" id="del2">删除</button>
	</div>
	<div>
		<table id="grid3"></table>
		<div id="page3"></div>
	</div>
</div>

<script type="text/javascript" src="${path}/web/base/user/js/setRole2UserPage.js"></script>
<link rel="stylesheet" type="text/css" href="${path}/web/base/user/css/setRole2UserPage.css" />