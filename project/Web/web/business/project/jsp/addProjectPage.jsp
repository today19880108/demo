<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="path" value="${pageContext.request.contextPath}" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>新增项目页面</title>
<jsp:include page="../../../comm/jsp/includeJquery.jsp" flush="true"></jsp:include>
<jsp:include page="../../../comm/jsp/includeJqGrid.jsp" flush="true"></jsp:include>
<jsp:include page="../../../comm/jsp/includeZtree.jsp" flush="true"></jsp:include>
<jsp:include page="../../../comm/jsp/includeCommon.jsp" flush="true"></jsp:include>
<jsp:include page="../../../comm/jsp/includeMultiselect.jsp" flush="true"></jsp:include>
<script type="text/javascript" src="${path}/ui/fileupload/ajaxfileupload.js"></script>  
<script type="text/javascript" src="${path}/ui/jquery/jquery.autocomplete.min.js"></script>  
<script type="text/javascript" src="${path}/web/business/project/js/addProjectPage.js"></script>
<link rel="stylesheet" type="text/css" href="${path}/web/business/project/css/addProjectPage.css"></link>
<script type="text/javascript">
var path = '${path}'; 
</script>
</head>
<body>
	<div class="containt">	
		<div class="border">
			<c:if test="${param.SEARCH_FLAG!='SEARCH'}">
			<input type="button" value="保存" class="button" id="save"/>
			</c:if>
			<input type="button" value="返回" class="button" id="return"/>
		</div>
		<div class="border">
			<%-- 
			<input type="hidden" name="now_year" id="now_year" value="${now_year}"/>
			<input type="hidden" name="now_planyear" id="now_planyear" value="${selectp.planyear}"/>
			<input type="hidden" name="now_projecttype2" id="now_projecttype2" value="${selectp.projecttype2}"/>
			--%>
			<input type="hidden" name="SEARCH_FLAG" id="SEARCH_FLAG" value="${param.SEARCH_FLAG}"/>
			<form id="addProjectForm">
			<input type="hidden" name="projectid" id="projectid" value="${selectp.projectid}"/>
			<ul class="addUpd">
				<li style="width:100%">
					<span>项目名称：</span>
					<input style="width:711px;" type="text" name="projectname" id="projectname" value="${selectp.projectname}" 
					validate="{isRequired: true, maxLen: 150}" 
					isRequired="请输入项目名称！"
					maxLen="项目名称最长150字符！"
					/>
					<c:if test="${param.SEARCH_FLAG!='SEARCH'}">
					<b class="title_css">*</b>
					</c:if>
				</li>
				<li style="width:100%;">
					<span>年度大计划：</span>
					<c:if test="${param.SEARCH_FLAG!='SEARCH'}">
					<select id="isyearplan" multiple="multiple" style="width:712px;*width:718px;display:none;">
						<option value="否">否</option>
						<c:forEach var="varO" items="${now_yearList}">
						<option value="${varO}" <c:if test="${fn:contains(selectp.planyear, varO)}">selected='selected'</c:if>>${varO}</option>
						</c:forEach>
					</select>
					</c:if>
					<c:if test="${param.SEARCH_FLAG=='SEARCH'}">
					<input style="width:711px;" type="text" value="${selectp.planyear}"/>
					</c:if>
					<input type="hidden" name="planyear" id="planyear" value="${selectp.planyear}"
					validate="{maxLen: 500}"
					maxLen="年度大计划选择过多！"
					/>
					<%-- 
					<script>
					$("#addProjectForm #isyearplan").val("${selectp.isyearplan}");
					</script>
					<select name="planyear" id="planyear" style="width: 70px; <c:if test="${selectp.isyearplan!=1}"> display:none;</c:if>">
					
					</select>
					<label style="width: 70px; <c:if test="${selectp.isyearplan!=1}"> display:none;</c:if>">年</label>
					--%>
				</li>
				<li>
					<span>分管市领导：</span>
					<input type="text" name="cityleaders" id="cityleaders" value="${selectp.cityleaders}" 
					validate="{maxLen: 150}" 
					maxLen="分管市领导最长150字符！"
					/>
				</li>
				<li>
					<span>责任处室：</span>
					<select name="managementoffice" id="managementoffice">
						<option value="">---请选择---</option>
						<c:forEach var="varO" items="${managementoffices}">
						<option value="${varO.ID}">${varO.NAME}</option>
						</c:forEach>
					</select>
					<script>
					$("#addProjectForm #managementoffice").val("${selectp.managementoffice}");
					</script>	
				</li>
				<li style="width:100%">
					<span>责任单位：</span>
					<input style="width:711px;" type="text" name="responsibilityunit" id="responsibilityunit" value="${selectp.responsibilityunit}"
					validate="{maxLen: 150}" 
					maxLen="责任单位最长150字符！"
					/>
				</li>
				<li style="width:100%">
					<span>代建单位：</span>
					<input style="width:711px;" type="text" name="insteadunit" id="insteadunit" value="${selectp.insteadunit}"
					validate="{maxLen: 150}" 
					maxLen="代建单位最长150字符！"
					/>
				</li>
				<li style="width:100%;">
					<span>辖区政府：</span>
					<c:if test="${param.SEARCH_FLAG!='SEARCH'}">
					<select id="selectLocalgoverment" multiple="multiple" style="width:712px;*width:718px;display:none;">
						<c:forEach var="varO" items="${localgoverments}">
						<option value="${varO.ID}" <c:if test="${fn:contains(selectp.localgoverment, varO.ID)}">selected='selected'</c:if>>${varO.NAME}</option>
						</c:forEach>
					</select>
					</c:if>
					<c:if test="${param.SEARCH_FLAG=='SEARCH'}">
					<input style="width:711px;" type="text" value="${selectp.localgoverment_name}"/>
					</c:if>
					<input type="hidden" name="localgoverment" id="localgoverment" value="${selectp.localgoverment}"
					validate="{maxLen: 500}" 
					maxLen="辖区政府选择过多！"
					/>
					<%-- 
					<script>
					$("#addProjectForm #localgoverment").val("${selectp.localgoverment}");
					</script>	
					--%>
				</li>
				<li style="width:100%;">
					<span>项目类别：</span>
					<input style="width:711px;" type="text" id="projecttypeTree" value="${selectp.projecttype_name}"/>
					<input type="hidden" name="projecttype" id="projecttype" value="${selectp.projecttype}"/>
					<%--
					<select name="projecttype" id="projecttype">
						<option value="">---请选择---</option>
						<c:forEach var="varO" items="${projecttype}">
						<option value="${varO.ID}">${varO.NAME}</option>
						</c:forEach>
					</select>
					<script>
					$("#addProjectForm #projecttype").val("${selectp.projecttype}");
					</script>
					<select name="projecttype2" id="projecttype2">
						<option value="">---请选择---</option>
					</select>
					--%>
				</li>
				<li>
					<span>建设性质：</span>
					<select name="constructionnature" id="constructionnature">
						<option value="">---请选择---</option>
						<c:forEach var="varO" items="${constructionNatures}">
						<option value="${varO.ID}">${varO.NAME}</option>
						</c:forEach>
					</select>
					<script>
					$("#addProjectForm #constructionnature").val("${selectp.constructionnature}");
					</script>	
				</li>
				<li>
					<span>建设阶段：</span>
					<select name="projectstage" id="projectstage">
						<option value="">---请选择---</option>
						<c:forEach var="varO" items="${projectstageList}">
						<option value="${varO.ID}">${varO.NAME}</option>
						</c:forEach>
					</select>
					<script>
					$("#addProjectForm #projectstage").val("${selectp.projectstage}");
					</script>	
				</li>
				<li>
					<span>建设期限：</span>
					<input type="text" name="starttime" id="starttime" value="${selectp.starttime}"
					validate="{checkDate: true}" 
					checkDate="建设期限输入有误！"
					style="width:110.5px;*width:111px;width:111px\0;"
					/>
					至
					<input type="text" name="endtime" id="endtime" value="${selectp.endtime}"
					validate="{checkDate: true}" 
					checkDate="建设期限输入有误！"
					style="width:110.5px;*width:111px;width:111px\0;"
					/>
				</li>
				<li class="all_width">
					<span>建设内容：</span>
					<textarea name="constructioncontent" id="constructioncontent"
					validate="{maxLen: 500}"
					maxLen="建设内容最长500字符！"
					>${selectp.constructioncontent}</textarea>
				</li>
				<c:if test="${param.SEARCH_FLAG=='SEARCH'}">
				<li>
					<span>总投资：</span>
					<input type="text" name="totalinvestment" id="totalinvestment" value="${selectp.totalinvestment}"/> 万元
				</li>
				<li>
					<span>已下达资金：</span>
					<input type="text" name="actualinvest" id="actualinvest" value="${selectp.actualinvest}"/> 万元
				</li>
				</c:if>
				<li class="all_width">
					<span>备注：</span>
					<textarea name="remark" id="remark"
					validate="{maxLen: 500}"
					maxLen="备注最长500字符！"
					>${selectp.remark}</textarea>
				</li>
				<%--
				<li style="width: 100%;<c:if test="${param.SEARCH_FLAG=='SEARCH'}"> display:none;</c:if>">
					<span>附件上传：</span>
					<input type="file" id="myFile" name="myFile" style="width: 715px;padding: 1px; height: 25px; line-height: 25px;border: solid 1px #d4d4d4;"/>
				</li>
				<li style="width: 100%; <c:if test="${selectp.filepath==null}"> display:none;</c:if>" id="fileDown_li">
					<span>附件下载：</span>
					<div id="fileDown_name" style="float: left;width: 651px;height: 24px;line-height: 24px;">${selectp.filepath}</div>
					<input type="button" value="下载" id="fileDown" style="width: 62px;height: 24px;line-height: 24px; margin-left: 5px;"/>
				</li>
				--%>
			</ul>
			</form>
		</div>
		<%-- 
		<div class="border">
			<c:if test="${param.SEARCH_FLAG!='SEARCH'}">
			<input type="button" value="新增建设" class="button" id="add2"/>
			<input type="button" value="修改" class="button" id="upd2"/>
			<input type="button" value="删除" class="button" id="del2"/>
			</c:if>
			<input type="button" value="导出Excel" class="button search" id="exportExcelStage"/>
		</div>
		<div>
			<table id="stagegrid"></table>
			<div id="stagepage"></div>
		</div>
		--%>
		<div class="border" style="margin-top: 5px;">
			<c:if test="${param.SEARCH_FLAG!='SEARCH'}">
			<input type="button" value="新增审批" class="button" id="addExamine"/>
			<input type="button" value="修改" class="button" id="updExamine"/>
			<input type="button" value="删除" class="button" id="delExamine"/>
			</c:if>
			<input type="button" value="导出Excel" class="button search" id="exportExamineExcel"/>
		</div>
		<div>
			<table id="examinegrid"></table>
			<div id="examinepage"></div>
		</div>
		<div class="border" style="margin-top: 5px;">
			<c:if test="${param.SEARCH_FLAG!='SEARCH'}">
			<input type="button" value="新增投资" class="button" id="add"/>
			<input type="button" value="修改" class="button" id="upd"/>
			<input type="button" value="删除" class="button" id="del"/>
			</c:if>
			<input type="button" value="导出Excel" class="button search" id="exportExcel"/>
		</div>
		<div>
			<table id="grid"></table>
			<div id="page"></div>
		</div>
	</div>
	<form id="downForm"></form>
</body>
</html>

