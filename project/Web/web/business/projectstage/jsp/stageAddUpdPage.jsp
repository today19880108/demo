<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!-- <jsp:include page="../../../comm/jsp/includeJquery.jsp" flush="true"></jsp:include>
<jsp:include page="../../../comm/jsp/includeJqGrid.jsp" flush="true"></jsp:include>
<jsp:include page="../../../comm/jsp/includeZtree.jsp" flush="true"></jsp:include>
<jsp:include page="../../../comm/jsp/includeCommon.jsp" flush="true"></jsp:include> -->
<c:set var="path" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
var path = '${path}'; 
</script>
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
		<input type="hidden" name="SEARCH_FLAG3" id="SEARCH_FLAG3" value="${param.SEARCH_FLAG}"/>
		<form id="stageAddUdpForm">
		<input type="hidden" name="stageid" id="stageid" value="${select.stageid}"/>
		<input type="hidden" name="projectid" id="projectid" value="${selectpr.projectid}"/>
		<ul class="addUpd">
			<li>
				<span>建设阶段：</span>
				<select name="stage" id="stage">
					<option value="">---请选择---</option>
					<c:forEach var="varO" items="${stages}">
					<option value="${varO.ID}">${varO.NAME}</option>
					</c:forEach>
				</select>
				<script>
				$("#stageAddUdpForm #stage").val("${select.stage}");
				</script>
			</li>
			<li>
				<span>负责人：</span>
				<input type="text" name="responsibleperson" id="responsibleperson" value="${select.responsibleperson}" 
				validate="{maxLen: 30}" 
				maxLen="负责人最长30字符！"/>
			</li><li>
				<span>开始日期：</span>
					<input type="text" name="starttime" id="starttime2" value="${select.starttime}"
					validate="{checkDate: true}" 
					checkDate="开始日期输入有误！" />
			</li>
			<li>
				<span>结束日期：</span>
					<input type="text" name="endtime" id="endtime2" value="${select.endtime}"
					validate="{checkDate: true}" 
					checkDate="结束日期输入有误！"
					/>
			</li>
			<li class="all_width">
				<span>阶段总结：</span>
				<textarea name="stagesummary" id="stagesummary"
				validate="{maxLen: 500}"
				maxLen="阶段总结描述最长500字符！"
				>${select.stagesummary}</textarea>
			</li>
			<li class="all_width">
				<span>备注：</span>
				<textarea name="remarks" id="remarks"
				validate="{maxLen: 500}"
				maxLen="备注最长500字符！"
				>${select.remarks}</textarea>
			</li>
		</ul>
		</form>
	</div>
</div>

<script type="text/javascript" src="${path}/web/business/projectstage/js/stageAddUpdPage.js"></script>
<link rel="stylesheet" type="text/css" href="${path}/web/business/projectstage/js/stageAddUpdPage.css" />
