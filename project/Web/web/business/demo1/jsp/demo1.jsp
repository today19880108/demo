<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="path" value="${pageContext.request.contextPath}" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的档案-个人信息</title>
<jsp:include page="../../../comm/jsp/includeJquery.jsp" flush="true"></jsp:include>
<jsp:include page="../../../comm/jsp/includeCommon.jsp" flush="true"></jsp:include>
<script type="text/javascript" src="${path}/web/business/demo1/js/demo1.js"></script>
<link rel="stylesheet" type="text/css" href="${path}/web/business/demo1/css/demo1.css"></link>
<script type="text/javascript">
var path = '${path}';
</script>
</head>
<body>
<div class="areaHead">
	<div class="title"><span class="icon matter_icon"></span><span>基本信息</span></div>
	<div class="oprate"><span class="icon upd_icon"></span><a href="javascript:;">修改</a></div>
</div>
<div class="baseInfo">
	<div class="left">
		<form>
		<ul>
			<li class="infoLi">
				<span class="title">姓名：</span>
				<input type="text" name="NAME"/>
			</li>
			<li class="infoLi">
				<span class="title">性别：</span>
				<span class="radio_title">男</span>
				<input type="radio" name="SEX" class="radio_"/>
				<span class="radio_title">女</span>
				<input type="radio" name="SEX" class="radio_"/>
			</li>
			<li class="infoLi">
				<span class="title">年龄：</span>
				<input type="text" name="AGE"/>
			</li>
			<li class="infoLi">
				<span class="title">身份证：</span>
				<input type="text" name="CODE"/>
			</li>
			<li class="infoLi">
				<span class="title">学历：</span>
				<input type="text" name="EDUCATIONAL"/>
			</li>
			<li class="infoLi">
				<span class="title">学位：</span>
				<input type="text" name="DEGREE"/>
			</li>
			<li class="infoLi">
				<span class="title">毕业院校：</span>
				<input type="text" name="GRADUATION"/>
			</li>
			<li class="infoLi">
				<span class="title">专业：</span>
				<input type="text" name="SPECIALTY"/>
			</li>
			<li class="infoLi_l">
				<span class="title">专业技能：</span>
				<input type="text" name="PROFESSIONAL"/>
			</li>
			<li class="infoLi_l">
				<span class="title">特长：</span>
				<input type="text" name="MERIT"/>
			</li>
			<li class="infoLi_l">
				<span class="title">兴趣爱好：</span>
				<input type="text" name="INTERESTS"/>
			</li>
			<li class="infoLi_area">
				<span class="title">自我评价：</span>
				<textarea name="EVALUATION"></textarea>
			</li>
			<li class="infoLi_button">
				<button id="save" class="save">保 存</button>
				<button id="cancel" class="cancel">取 消</button>
			</li>
		</ul>
		</form>
	</div>
	<div class="right">
		<div class="avatarArea">
			<img id="avatar" class="avatar" src="${path}/${requestScope.avatarPath}/demo1.png" />
			<span class="caption">
			支持JPG、PNG、GIF格式图片上传
			<a href="javascript:;">选择上传</a>
			</span>
		</div>
	</div>
</div>
<div class="areaHead">
	<div class="title"><span class="icon matter_icon"></span><span>教育背景</span></div>
	<div class="oprate"><span class="icon add_icon"></span><a href="javascript:;">新增</a></div>
</div>
</body>
</html>