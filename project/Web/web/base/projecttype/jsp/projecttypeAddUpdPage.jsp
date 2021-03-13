<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<c:set var="path" value="${pageContext.request.contextPath}" />

<div class="dia_containt">	
	<div class="border">
		<input type="hidden" name="SEARCH_FLAG" id="SEARCH_FLAG" value="${param.SEARCH_FLAG}"/>
		<form id="menuAddUdpForm">
		<input type="hidden" name="id" id="id" value="${projecttype.id}"/>
		<ul class="addUpd">
			<li>
				<span>上级类别：</span>
				<input type="text" name="PID_NAME" id="PID_NAME2" value="${projecttype.pid_name}" />
				<input type="hidden" name="pid" id="PID2" value="${projecttype.pid}" />
				<a href="javascript:;" id="SELECT_BUT" class="select_but">选择</a>
			</li>
			<li>
				<span>类别名称：</span>
				<input type="text" name="name" id="name" value="${projecttype.name}" 
				validate="{isRequired: true, maxLen: 100}" 
				isRequired="请输入项目类别名称！"
				maxLen="类别名称最长100字符！"/>
				<c:if test="${param.SEARCH_FLAG!='SEARCH'}">
				<b class="title_css">*</b>
				</c:if>
			</li><li>
				<span>显示顺序：</span>
				<input type="text" name="ord" id="ord" value="${projecttype.ord}"
				validate="{isDigit: true}"
				isDigit="请输入整数的显示顺序！"
				/>
			</li>
			<li class="all_width">
				<span>项目类别描述：</span>
				<textarea name="remark" id="REMARK"
				validate="{maxLen: 100}"
				maxLen="项目类别描述最长100字符！"
				>${projecttype.remark}</textarea>
			</li>
		</ul>
		</form>
	</div>
</div>

<script type="text/javascript" src="${path}/web/base/projecttype/js/projecttypeAddUpdPage.js"></script>
<link rel="stylesheet" type="text/css" href="${path}/web/base/projecttype/css/projecttypeAddUpdPage.css" />
