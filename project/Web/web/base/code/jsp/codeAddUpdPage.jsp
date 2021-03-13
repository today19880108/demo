<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<c:set var="path" value="${pageContext.request.contextPath}" />

<div class="dia_containt">	
	<div class="border">
		<input type="hidden" name="SEARCH_FLAG" id="SEARCH_FLAG" value="${param.SEARCH_FLAG}"/>
		<form id="codeAddUdpForm">
		<input type="hidden" name="CODE_ID" id="CODE_ID" value="${codeObj.CODE_ID}"/>
		<ul class="addUpd">
			<li>
				<span>字典标识：</span>
				<input type="text" name="ID" id="ID" value="${codeObj.ID}" 
				validate="{isRequired: true, isDigitOrABC: true, maxLen: 50}" 
				isRequired="请输入由数字或字母组成的字典标识！"
				isDigitOrABC="请输入由数字或字母组成的字典标识！"
				maxLen="字典标识最长50字符！"/>
				<c:if test="${param.SEARCH_FLAG!='SEARCH'}">
				<b class="title_css">*</b>
				</c:if>
			</li>
			<li>
				<span>字典名称：</span>
				<input type="text" name="NAME" id="NAME" value="${codeObj.NAME}" 
				validate="{isRequired: true,maxLen: 30}" 
				isRequired="请输入字典名称！"
				maxLen="字典名称最长30字符！"/>
				<c:if test="${param.SEARCH_FLAG!='SEARCH'}">
				<b class="title_css">*</b>
				</c:if>
			</li>
			<li>
				<span>字典类型：</span>
				<select name="TYPE" id="TYPE"
				validate="{isRequired: true}"
				isRequired="请选择字典类型！">
					<option value="">---请选择---</option>
					<c:forEach var="varO" items="${types}">
					<option value="${varO.ID}">${varO.NAME}</option>
					</c:forEach>
				</select>
				<script>
				$("#codeAddUdpForm #TYPE").val("${codeObj.TYPE}");
				</script>
				<c:if test="${param.SEARCH_FLAG!='SEARCH'}">
				<b class="title_css">*</b>
				</c:if>
			</li>
			<li>
				<span>显示顺序：</span>
				<input type="text" name="ORD" id="ORD" value="${codeObj.ORD}" 
				validate="{isDigit: true}"
				isDigit="请输入整数的显示顺序！"/>
			</li>
		</ul>
		</form>
	</div>
</div>

<script type="text/javascript" src="${path}/web/base/code/js/codeAddUpdPage.js"></script>
<link rel="stylesheet" type="text/css" href="${path}/web/base/code/css/codeAddUpdPage.css" />
