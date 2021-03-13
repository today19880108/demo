<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<div class="dia_containt">	
<%--
	<div class="border">
		<ul class="addUpd">
			<li style="width:100%">
				<span>项目名称：</span>
				<input type="text" name="projectname" id="projectname" value="${selectpr.projectname}" style="width: 584px;" disabled/>
			</li>
			<li>
				<span>建设期限：</span>
				<input type="text" name="deadline" id="deadline" value="${selectpr.deadline}" disabled/>
			</li>
			<li>
				<span>总投资：</span>
				<input type="text" name="totalinvestment" id="totalinvestment" value="${selectpr.totalinvestment}" disabled/>
			</li>
		</ul>
	</div>
--%>	
	<div class="border">
		<input type="hidden" name="year" id="year" value="${year}"/>
		<input type="hidden" name="SEARCH_FLAG2" id="SEARCH_FLAG2" value="${param.SEARCH_FLAG}"/>
		<form id="addInvestForm">
		<input type="hidden" name="investmentid" id="investmentid" value="${selectp.investmentid}"/>
		<input type="hidden" name="projectid" id="projectid" value="${selectpr.projectid}"/>
		<ul class="addUpd" id="addInvestFormUL">
			<li>
				<span>文件名称：</span>
				<input type="text" name="referencename" id="referencename" value="${selectp.referencename}" 
				validate="{maxLen: 150}" 
				maxLen="文件名称最长150字符！"/>
			</li>
			<li>
				<span>文号：</span>
				<input type="text" name="referencecode" id="referencecode" value="${selectp.referencecode}" 
				validate="{maxLen: 150}" 
				maxLen="文号最长150字符！"/>
			</li>
			<li>
				<span>下达时间：</span>
			 	<input type="text" name="investmenttime" id="investmenttime" value="${selectp.investmenttime}"
				validate="{checkDate: true}" 
				checkDate="下达时间输入有误！"
				/>
			</li>
			<li>
				<span>资金来源：</span>
				<select name="funding" id="funding">
					<option value="">---请选择---</option>
					<c:forEach var="varO" items="${fundings}">
					<option value="${varO.ID}">${varO.NAME}</option>
					</c:forEach>
				</select>
				<input type="hidden" id="funding" value="${selectp.funding}"/>
				<script>
				$("#addInvestForm #funding").val("${selectp.funding}");
				</script>
			</li>
			<%-- 
			<li>
				<span>计划资金：</span>
				<input type="text" name="planinvest" id="planinvest" value="${selectp.planinvest}"
				validate="{isNumber: true}"
				isNumber="计划资金请输入数字！" /> 万元
			</li>
			--%>
			<li>
				<span>下达资金：</span>
				<input type="text" name="actualinvest" id="actualinvest" value="${selectp.actualinvest}"
				validate="{isNumber: true}"
				isNumber="下达资金请输入数字！" /> 万元
			</li>
			<%--
			<li class="all_width">
				<span>投资目标：</span>
				<textarea name="investtarget" id="investtarget"
				validate="{maxLen: 500}" style="width:585px"
				maxLen="投资目标最长500字符！"
				>${selectp.investtarget}</textarea>
			</li>
			--%>
			<li class="all_width">
				<span>备注：</span>
				<textarea name="remarks" id="remarks"
				validate="{maxLen: 500}" style="width:585px"
				maxLen="备注最长500字符！"
				>${selectp.remarks}</textarea>
			</li>
			<li style="width: 100%;<c:if test="${param.SEARCH_FLAG=='SEARCH'}"> display:none;</c:if>">
				<span>附件上传：</span>
				<input type="file" id="myFile3" name="myFile3" style="width: 589px;padding: 1px; height: 25px; line-height: 25px;border: solid 1px #d4d4d4;"/>
			</li>
			<%-- 
			<li style="width: 100%; <c:if test="${selectp.filepath==null}"> display:none;</c:if>" id="fileDown_li">
				<span>附件下载：</span>
				<div id="fileDown_name3" style="float: left;width: 527px;height: 24px;line-height: 24px;">${selectp.filepath}</div>
				<input type="button" value="下载" id="fileDown3" style="width: 62px;height: 24px;line-height: 24px; margin-left: 5px;<c:if test="${param.SEARCH_FLAG!='SEARCH'}"> display:none;</c:if>"/>
			</li>
			--%>
		</ul>
		</form>
	</div>
</div>

<script type="text/javascript" src="${path}/web/business/project/js/investManage.js"></script>


