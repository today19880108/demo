<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="path" value="${pageContext.request.contextPath}" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>分析页面</title>
<jsp:include page="../../../comm/jsp/includeJquery.jsp" flush="true"></jsp:include>
<jsp:include page="../../../comm/jsp/includeJqplot.jsp" flush="true"></jsp:include>
<jsp:include page="../../../comm/jsp/includeCommon.jsp" flush="true"></jsp:include>
<script type="text/javascript" src="${path}/web/business/analyse/js/analysePage.js"></script>
<link rel="stylesheet" type="text/css" href="${path}/web/business/analyse/css/analysePage.css" />
<script type="text/javascript">
var path = '${path}';
</script>
</head>
<body>
	<div class="containt">	
		<input type="hidden" id="year" value='${year}' />
		<input type="hidden" id="projectTypeList" value='${projectTypeList}' />
		<div class="border" style="float: left; margin: 10px; padding:10px">
			<div style="height: 18px; font-weight: bold; margin-bottom: 10px;">年度总投资分析
				&nbsp;
				<select id="year_s">
					
				</select>
				-
				<select id="year_e">
					
				</select>
				<span style="font-weight: normal;">年份</span>
			</div>
			<div id="lineId" style="height: 300px; width: 460px;"></div> 
		</div>
		<div class="border" style="float: left; margin: 10px; padding:10px">
			<div style="height: 18px; font-weight: bold; margin-bottom: 10px;">项目类别投资分析</div>
			<div id="barId" style="height: 300px; width: 460px;"></div> 
		</div>
		<div class="border" style="float: left; margin: 10px; padding:10px">
			<div style="height: 18px; font-weight: bold; margin-bottom: 10px;">年度项目类别投资分析
				&nbsp;
				<select id="year_s2">
					
				</select>
				-
				<select id="year_e2">
					
				</select>
				<span style="font-weight: normal;">年份</span>
			</div>
			<div id="lineNumId" style="height: 300px; width: 960px;"></div> 
		</div>
	</div>
</body>
</html>