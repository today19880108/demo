<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<div class="dia_containt">	
	<div class="border">
		<input type="hidden" name="SEARCH_FLAG3" id="SEARCH_FLAG3" value="${param.SEARCH_FLAG}"/>
		<form id="examinestageAddUdpForm">
		<input type="hidden" name="examineid" id="examineid" value="${select.examineid}"/>
		<input type="hidden" name="projectid" id="projectid" value="${select.projectid}"/>
		<ul class="addUpd" id="examinestageAddUdpFormUL">
			<li>
				<span>审批阶段：</span>
				<select name="stage" id="stage" validate="{isRequired: true}" isRequired="请选择审批阶段！">
					<option value="">---请选择---</option>
					<c:forEach var="varO" items="${stages}">
					<option value="${varO.ID}">${varO.NAME}</option>
					</c:forEach>
				</select>
				<script>
				$("#examinestageAddUdpForm #stage").val("${select.stage}");
				</script>
				<c:if test="${param.SEARCH_FLAG!='SEARCH'}">
				<b class="title_css">*</b>
				</c:if>
			</li>
			<li>
				<span>文件名称：</span>
				<input type="text" name="documentname" id="documentname" value="${select.documentname}" 
				validate="{maxLen: 150}" 
				maxLen="文件名称最长150字符！"/>
			</li>
			<li>
				<span>文号：</span>
				<input type="text" name="dnumber" id="dnumber" value="${select.dnumber}" 
				validate="{maxLen: 150}" 
				maxLen="文号最长150字符！"/>
			</li>
			<li>
				<span>审批时间：</span>
				<input type="text" name="replytime" id="replytime" value="${select.replytime}"
				validate="{checkDate: true}" 
				checkDate="审批时间输入有误！"/>
			</li>
			<li>
				<span>审批金额 ：</span>
				<input type="text" name="replymoney" id="replymoney" value="${select.replymoney}" 
				 validate="{isRequired: true,isNumber: true}"
				isNumber="审批金额请输入数字！" isRequired="请输入审批金额！"/>
				万元
				<c:if test="${param.SEARCH_FLAG!='SEARCH'}">
				<b class="title_css">*</b>
				</c:if>
			</li>
			
			<li class="all_width" >
				<span>备注：</span>
				<textarea name="remark" id="remark"
				validate="{maxLen: 500}"
				maxLen="备注最长500字符！"
				>${select.remark}</textarea>
			</li>
			<li style="width: 100%;<c:if test="${param.SEARCH_FLAG=='SEARCH'}"> display:none;</c:if>">
				<span>附件上传：</span>
				<input type="file" id="exmyFile" name="exmyFile" style="width: 589px;padding: 1px; height: 25px; line-height: 25px;border: solid 1px #d4d4d4;"/>
			</li>
			</ul>
		</form>
	</div>
</div>

<script type="text/javascript" src="${path}/web/business/examinestage/js/stageAddUpdPage.js"></script>
 