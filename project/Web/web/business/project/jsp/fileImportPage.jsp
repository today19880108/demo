<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<script type="text/javascript" src="${path}/ui/fileupload/ajaxfileupload.js"></script>

<div class="dia_containt">
	<div class="border">
		<form id="addProjectForm">
			<div style="margin-top: 20px;">
				<input type="file" id="myFile" name="myFile" style="width: 600px;border: solid 1px #d4d4d4;padding: 1px; margin-left: 40px;height: 25px; line-height: 25px;"/>
				<input type="button" value="导入" style="width: 80px; height: 24px; line-height: 24px;margin-left: 10px;" id="importBut"  onclick="fileupload()"/>
			</div>
			<div style="margin: 20px 0 10px 0;text-align: center;font-size: 15px;">
				<a href='javascript:dowloadFile()' style="margin: 0 auto;text-decoration: underline;color: #0073EA;">项目信息导入模板下载.xls</a>
			</div>
		</form>
	</div>
</div>

<script type="text/javascript">
	var path = '${path}'; 
	
	//模板下载
	function dowloadFile(){
		var url = path+'/business/downloadTemplet.do';
		window.location.href= url;
	}
	function fileupload(){ 
		var myFile = $("#myFile").val();
		if(myFile.length > 0) {
			var l1 = myFile.indexOf('.xls');
			var l2 = myFile.indexOf('.xlsx');
			if(l1 < 0&&l2 < 0){
				myDialog("只支持Excel文件(xls/xlsx)导入！", 0);
				return;
			}
			$.ajaxFileUpload({
				url : path+ '/business/fileImport.do', 
				secureuri : false,
				fileElementId : 'myFile',
				dataType : 'text', // or json xml whatever you
				success : function(data, status) {
					if (data != "0") {
						myDialog("导入项目失败，请严格按照项目信息导入模板样例来填写！", 0);
						return;
					}
					myDialog("导入成功！", 1);
	   	 			$("#consoleId").dialog("close");
	   	 			$("#grid").trigger("reloadGrid");
				}
			});
		}else{
			myDialog("请选择Excel文件(xls/xlsx)！", 0);
		}
	}
</script>

