<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<c:set var="path" value="${pageContext.request.contextPath}" />

<div class="dia_containt">	
	<div class="border">
		<input type="hidden" name="SEARCH_FLAG" id="SEARCH_FLAG" value="${param.SEARCH_FLAG}"/>
		<form id="menuAddUdpForm">
		<input type="hidden" name="ID" id="ID" value="${menuObj.ID}"/>
		<ul class="addUpd">
			<li>
				<span>上级菜单：</span>
				<input type="text" name="PID_NAME2" id="PID_NAME2" value="${menuObj.PID_NAME}" />
				<input type="hidden" name="PID2" id="PID2" value="${menuObj.PID}" />
				<a href="javascript:;" id="SELECT_BUT" class="select_but">选择</a>
			</li>
			<li>
				<span>菜单名称：</span>
				<input type="text" name="NAME" id="NAME" value="${menuObj.NAME}" 
				validate="{isRequired: true, maxLen: 30}" 
				isRequired="请输入菜单名称！"
				maxLen="菜单名称最长30字符！"/>
				<c:if test="${param.SEARCH_FLAG!='SEARCH'}">
				<b class="title_css">*</b>
				</c:if>
			</li>
			<li>
				<span>菜单路径：</span>
				<input type="text" name="PATH" id="PATH" value="${menuObj.PATH}"
				validate="{maxLen: 100}"
				maxLen="菜单路径最长100字符！"/>
			</li>
			<li>
				<span>显示顺序：</span>
				<input type="text" name="ORD" id="ORD" value="${menuObj.ORD}"
				validate="{isDigit: true}"
				isDigit="请输入整数的显示顺序！"
				/>
			</li>
			<li class="all_width">
				<span>菜单描述：</span>
				<textarea name="REMARK" id="REMARK"
				validate="{maxLen: 100}"
				maxLen="菜单描述最长100字符！"
				>${menuObj.REMARK}</textarea>
			</li>
		</ul>
		</form>
	</div>
</div>

<script type="text/javascript" src="${path}/web/base/menu/js/menuAddUpdPage.js"></script>
<link rel="stylesheet" type="text/css" href="${path}/web/base/menu/css/menuAddUpdPage.css" />
