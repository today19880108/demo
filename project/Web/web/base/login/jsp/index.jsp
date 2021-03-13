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
<jsp:include page="../../../comm/jsp/includeZtree.jsp" flush="true"></jsp:include>
<jsp:include page="../../../comm/jsp/includeEasyui.jsp" flush="true"></jsp:include>
<script type="text/javascript" src="${path}/web/base/login/js/index.js"></script>
<link rel="stylesheet" type="text/css" href="${path}/web/base/login/css/index.css">
<script type="text/javascript" src="${path}/web/base/login/js/setPwdPage.js"></script>
<link rel="stylesheet" type="text/css" href="${path}/web/base/login/css/setPwdPage.css" />
<script type="text/javascript">
var path = '${path}';
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:72px;padding:0 10px;" class="head_north">
		<div style="height:19px">
			<div class="userInfoShow"><img src="${path}/web/base/login/image/user.png" />
				<span>欢迎您：${sessionScope.user.USER_NAME}</span>
			</div>
			<div class="showTime">
				<span>今天是：${showTime}</span>
			</div>
		</div>
		<div style="height:51px;" class="head_logo">
		<form id="signOut_form" method="post">
			<div class="signOut"><img src="${path}/web/base/login/image/signOut.png" />
				<a href="javascript:;" id="signOut">退出系统</a>
			</div>
			<div class="updPassword"><img src="${path}/web/base/login/image/changePwd.png" />
				<a href="javascript:;" id="setPwd">修改密码</a>
			</div>
		</form>
		</div>
	</div>
	<div data-options="region:'west',split:true,title:'业务菜单'" style="width:210px;">
		<div class="easyui-accordion" data-options="fit:true,border:false">
			<div title="历年投资项目库" style="padding:2px 8px;" data-options="selected:true">
				<ul id="tree1" class="ztree"></ul>
			</div>
		</div>
	</div>
	<!--<div data-options="region:'south',border:false" style="height:50px;padding:10px;">south region</div>-->
	<div data-options="region:'center',title:'<div id=titleSpan>业务内容 》综合查询 》投资项目查询<div>'" style="padding:5px;" id="main_content">
		<iframe src="${path}/business/projectManagePage.do?SEARCH_FLAG=SEARCH" frameborder="0" style="width:100%;height:100%;"></iframe>
	</div>
</body>
</html>