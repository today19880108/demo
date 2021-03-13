<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<c:set var="path" value="${pageContext.request.contextPath}" />

<div class="dia_containt">	
	<div class="border">
		<input type="hidden" name="SEARCH_FLAG" id="SEARCH_FLAG" value="${param.SEARCH_FLAG}"/>
		<form id="menuAddUdpForm">
		<input type="hidden" name="id" id="id" value="${depart.id}"/>
		<ul class="addUpd">
			<li>
				<span>上级部门：</span>
				<input type="text" name="PID_NAME" id="PID_NAME2" value="${depart.pid_name}" />
				<input type="hidden" name="pid" id="PID2" value="${depart.pid}" />
				<a href="javascript:;" id="SELECT_BUT" class="select_but">选择</a>
			</li>
			<li>
				<span>部门名称：</span>
				<input type="text" name="name" id="name" value="${depart.name}" 
				validate="{isRequired: true, maxLen: 100}" 
				isRequired="请输入部门名称！"
				maxLen="部门名称最长100字符！"/>
				<c:if test="${param.SEARCH_FLAG!='SEARCH'}">
				<b class="title_css">*</b>
				</c:if>
			</li>
			<li>
				<span>显示顺序：</span>
				<input type="text" name="ord" id="ord" value="${depart.ord}"
				validate="{isDigit: true}"
				isDigit="请输入整数的显示顺序！"
				/>
			</li>
			<li class="all_width">
				<span>部门描述：</span>
				<textarea name="remark" id="REMARK"
				validate="{maxLen: 100}"
				maxLen="部门描述最长100字符！"
				>${depart.remark}</textarea>
			</li>
		</ul>
		</form>
	</div>
</div>

<script type="text/javascript" src="${path}/web/base/department/js/departAddUpdPage.js"></script>
<link rel="stylesheet" type="text/css" href="${path}/web/base/department/css/departAddUpdPage.css" />
