<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="path" value="${pageContext.request.contextPath}" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>投资项目库</title>
<jsp:include page="../../../comm/jsp/includeJquery.jsp" flush="true"></jsp:include>
<jsp:include page="../../../comm/jsp/includeCommon.jsp" flush="true"></jsp:include>
<jsp:include page="../../../comm/jsp/includeEasyui.jsp" flush="true"></jsp:include>
<script type="text/javascript" src="${path}/web/business/search/js/searchNologin.js"></script>
<link rel="stylesheet" type="text/css" href="${path}/web/business/search/css/searchNologin.css"></link>
<script type="text/javascript">
var path = '${path}';
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'west',split:true,title:'投资项目查询'" style="width:210px;">
		<div class="easyui-accordion" data-options="fit:true,border:false">
			<div style="padding:2px 8px;" data-options="selected:true" id="treePanel">
				<div class="noLoginSearchLeftTree">
					<div class="leftTreeNum" name="leftTreeNum_searchByName">
						<div class="leftTreeNumImgP">
							<img class="leftTreeNumImg selectBorder" src="${path}/web/business/search/image/searchByName.png"/>
						</div>
						<div class="leftTreeNumTitle">项目名称查询</div>
					</div>
					<div class="leftTreeNum" name="leftTreeNum_searchByTime">
						<div class="leftTreeNumImgP">
							<img class="leftTreeNumImg" src="${path}/web/business/search/image/searchByTime.png" />
						</div>
						<div class="leftTreeNumTitle">时间区间查询</div>
					</div>
					<div class="leftTreeNum" name="leftTreeNum_searchByoffice">
						<div class="leftTreeNumImgP">
							<img class="leftTreeNumImg" src="${path}/web/business/search/image/searchByoffice.png" />
						</div>
						<div class="leftTreeNumTitle">责任处室查询</div>
					</div>
					<div class="leftTreeNum" name="leftTreeNum_searchByAll">
						<div class="leftTreeNumImgP">
							<img class="leftTreeNumImg" src="${path}/web/business/search/image/searchByAll.png" />
						</div>
						<div class="leftTreeNumTitle">综合查询</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--<div data-options="region:'south',border:false" style="height:50px;padding:10px;">south region</div>-->
	<div data-options="region:'center',title:''" style="padding:5px;" id="main_content">
		<%-- <iframe src="${path}/search/projectManagePage.do?SEARCH_FLAG=SEARCH&leftTreeNum=searchByName" frameborder="0" style="width:100%;height:100%;"></iframe> --%>
		<iframe src="${path}/base/indexHome.do" frameborder="0" style="width:100%;height:100%;"></iframe>
	</div>
</body>
</html>