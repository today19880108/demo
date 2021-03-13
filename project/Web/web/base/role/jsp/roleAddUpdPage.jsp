<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<c:set var="path" value="${pageContext.request.contextPath}" />

<div class="dia_containt">	
	<div class="border">
		<input type="hidden" name="SEARCH_FLAG" id="SEARCH_FLAG" value="${param.SEARCH_FLAG}"/>
		<form id="roleAddUdpForm">
		<input type="hidden" name="ID" id="ID" value="${roleObj.ID}"/>
		<ul class="addUpd">
			<li>
				<span>角色名称：</span>
				<input type="text" name="NAME" id="NAME" value="${roleObj.NAME}" 
				validate="{isRequired: true, maxLen: 30}" 
				isRequired="请输入角色名称！"
				maxLen="角色名称最长30字符！"/>
				<c:if test="${param.SEARCH_FLAG!='SEARCH'}">
				<b class="title_css">*</b>
				</c:if>
			</li>
			<li>
				<span>显示顺序：</span>
				<input type="text" name="ORD" id="ORD" value="${roleObj.ORD}"
				validate="{isDigit: true}"
				isDigit="请输入整数的显示顺序！"
				/>
			</li>
			<li class="all_width">
				<span>角色描述：</span>
				<textarea name="REMARK" id="REMARK"
				validate="{maxLen: 100}"
				maxLen="角色描述最长100字符！"
				>${roleObj.REMARK}</textarea>
			</li>
		</ul>
		</form>
	</div>
</div>

<script type="text/javascript" src="${path}/web/base/role/js/roleAddUpdPage.js"></script>
<link rel="stylesheet" type="text/css" href="${path}/web/base/role/css/roleAddUpdPage.css" />
