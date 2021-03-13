<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<jsp:include page="../../../comm/jsp/includeJqplot.jsp" flush="true"></jsp:include>

<c:set var="path" value="${pageContext.request.contextPath}" />
<input type="hidden" name="PROJECTID" id="PROJECTID" value="${param.PROJECTID}" />
<div class="dia_containt">	
	<div class="border" style="float: left; margin: 20px 10px ; padding:10px">
		<div style="font-weight: bold;">资金来源分析</div>
		<div id="dia_pieId" style="height: 280px; width: 400px;"></div> 
	</div>
	<div class="border" style="float: left; margin: 20px 10px; padding:10px">
		<div style="font-weight: bold;">年投资分析</div>
		<div id="dia_barId" style="height: 280px; width: 400px;"></div> 
	</div>
</div>

<script type="text/javascript" src="${path}/web/business/analyse/js/analysePageById.js"></script>
