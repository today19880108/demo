<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="path" value="${pageContext.request.contextPath}" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>项目管理页面</title>
<jsp:include page="../../../comm/jsp/includeJquery.jsp" flush="true"></jsp:include>
<jsp:include page="../../../comm/jsp/includeJqGrid.jsp" flush="true"></jsp:include>
<jsp:include page="../../../comm/jsp/includeZtree.jsp" flush="true"></jsp:include>
<jsp:include page="../../../comm/jsp/includeCommon.jsp" flush="true"></jsp:include>
<script type="text/javascript" src="${path}/web/business/project/js/projectManage.js"></script>
<link rel="stylesheet" type="text/css" href="${path}/web/business/project/css/projectManage.css"></link>
<script type="text/javascript">
var path = '${path}'; 
</script>
</head>
<body>
	<input type="hidden" name="SEARCH_FLAG" id="SEARCH_FLAG" value="${param.SEARCH_FLAG}"/>
	<input type="hidden" name="leftTreeNum" id="leftTreeNum" value="${param.leftTreeNum}"/>
	<div class="containt">	
		<div class="border">
			<form id="projectForm" method="post">
			<ul class="select">
				<c:if test="${param.leftTreeNum==null||param.leftTreeNum=='searchByName'}">
				<li>
					<span>项目名称：</span>
					<input type="text" name="projectname" id="projectname"/>
				</li>
				</c:if>
				<c:if test="${param.leftTreeNum==null}">
				<li>
					<span>年度大计划：</span>
					<select name="planyear" id="planyear">
						<option value="">---请选择---</option>
						<option value="否">否</option>
						<c:forEach var="varO" items="${now_yearList}">
						<option value="${varO}">${varO}</option>
						</c:forEach>
					</select>
				</li>
				<li>
					<span>分管市领导：</span>
					<input type="text" name="cityleaders" id="cityleaders"/>
				</li>
				</c:if>
				<c:if test="${param.leftTreeNum==null||param.leftTreeNum=='searchByoffice'}">
				<li>
					<span>责任处室：</span>
					<select name="managementoffice" id="managementoffice">
						<option value="">---请选择---</option>
						<c:forEach var="varO" items="${managementoffices}">
						<option value="${varO.ID}">${varO.NAME}</option>
						</c:forEach>
					</select>
				</li>
				</c:if>
				<c:if test="${param.leftTreeNum==null}">
				<li>
					<span>责任单位：</span>
					<input type="text" name="responsibilityunit" id="responsibilityunit"/>
				</li>
				<li>
					<span>代建单位：</span>
					<input type="text" name="insteadunit" id="insteadunit"/>
				</li>
				<li>
					<span>辖区政府：</span>
					<select name="localgoverment" id="localgoverment">
						<option value="">---请选择---</option>
						<c:forEach var="varO" items="${localgoverments}">
						<option value="${varO.ID}">${varO.NAME}</option>
						</c:forEach>
					</select>
				</li>
				<li>
					<span>项目类别：</span>
					<input type="text" id="projecttypeTree"/>
					<input type="hidden" name="projecttype" id="projecttype" value="${selectp.projecttype}"/>
				</li>
				<li>
					<span>建设性质：</span>
					<select name="constructionnature" id="constructionnature">
						<option value="">---请选择---</option>
						<c:forEach var="varO" items="${constructionNatures}">
						<option value="${varO.ID}">${varO.NAME}</option>
						</c:forEach>
					</select>
				</li>
				<li>
					<span>建设阶段：</span>
					<select name="projectstage" id="projectstage">
						<option value="">---请选择---</option>
						<c:forEach var="varO" items="${projectstageList}">
						<option value="${varO.ID}">${varO.NAME}</option>
						</c:forEach>
					</select>
				</li>
				</c:if>
				<c:if test="${param.leftTreeNum==null||param.leftTreeNum=='searchByTime'}">
				<li>
					<span>审批时间：</span>
					<input style="width:74.5px;" type="text" name="replytime_s" id="replytime_s"/>
					-
					<input style="width:74.5px;" type="text" name="replytime_e" id="replytime_e"/>
				</li>
				</c:if>
			</ul>
			</form>
		</div>
		
		<div class="border">
			<c:if test="${param.SEARCH_FLAG!='SEARCH'}">
			<input type="button" value="新增" class="button" id="add"/>
			<input type="button" value="修改" class="button" id="upd"/>
			<input type="button" value="删除" class="button" id="del"/>
			<input type="button" value="导入" class="button" id="import"/>
			</c:if>
			<input type="button" value="导出Excel" class="button search" id="exportExcel"/>
			<input type="button" value="查询" class="button search" style="margin-right: 0;" id="search"/>
		</div>
		<div>
			<table id="grid"></table>
			<div id="page"></div>
		</div>
	</div>
</body>
</html>