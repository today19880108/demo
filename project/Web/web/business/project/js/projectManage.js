$(function() {
	// 项目类别
//	$("#projecttype").bind("change", function(){
//		projecttypeChange($(this).val());
//	});
	myAjaxReqJson(path+'/comm/getTreeData.ajax', function(data){
		initSelectZtree({
			zNodes: data.list, 
			clickId: "projecttypeTree", 
			event: "click", 
			showObjId: "projecttypeTree", 
			valObjId: "projecttype",
			howShow: "firts",
			menuWidth: "noLimit",
			zindex: 5000
		});
	}, {name: "projecttype"}, true);
	$("#projecttypeTree").bind("keyup", function(e){
		if (e.keyCode==8&&$.trim($("#projecttypeTree").val()).length==0){
			$("#projecttype").val("");
			$("#projecttypeTree").val("");
		}
	});
	
	jQuery('[name*=replytime_]').datepicker({
		changeMonth: true, 
	    changeYear: true, 
	    dateFormat: "yy-mm-dd"
	});
	
	$("#add").button();
	$("#add").bind("click",
			function() {
				window.location.href = path + '/business/addProjectPage.do';
			}
	);

	$("#upd").button();
	$("#upd").bind(
			"click",
			function() {
				var selectedRowId = $("#grid").jqGrid("getGridParam",
						"selarrrow");
				if (!selectedRowId || selectedRowId.length == 0) {
					myDialog("请选择需要修改的记录!", 0);
					return false;
				} else if (selectedRowId.length > 1) {
					myDialog("您选择修改的记录过多!", 0);
					return false;
				}
				window.location.href = path + '/business/addProjectPage.do?projectid='+selectedRowId[0];
			});
	
	$("#del").button();
	$("#del").bind(
			"click",
			function() {
				var selectedRowId = $("#grid").jqGrid("getGridParam",
						"selarrrow");
				if (!selectedRowId || selectedRowId.length == 0) {
					myDialog("请选择需要删除的记录!", 0);
					return false;
				} else {
					myDialogConfirm("您确定要删除选中的记录嘛？", function() {
						myAjaxReqJson(path + '/business/delectProject.do',
								function(result, textStatus) {
									myDialog("删除成功！", 1);
									$("#grid").trigger("reloadGrid");
								}, {
									projectid : selectedRowId
								}, false);
					});
				}
			});
	
	$("#import").button();
	$("#import").bind("click", function(){
		addUpdDialog({
			htmlPath: path+'/business/fileImportPage.do', 
			title: "导入项目"
		});
	});
	
	$("#exportExcel").button();
	$("#exportExcel").bind("click", function(){
		$("#projectForm").attr("action", path+'/business/exportProject.do');
		$("#projectForm").submit();
	});
	
	$("#search").button();
	$("#search").bind("click", function() {
		var projectname = $.trim($("#projectname").val());
		var planyear = $.trim($("#planyear").val());
		var cityleaders = $.trim($("#cityleaders").val());
		var managementoffice = $.trim($("#managementoffice").val());
		var responsibilityunit = $.trim($("#responsibilityunit").val());
		var insteadunit = $.trim($("#insteadunit").val());
		var localgoverment = $.trim($("#localgoverment").val());
		var projecttype = $.trim($("#projecttype").val());
		var constructionnature = $.trim($("#constructionnature").val());
		var projectstage = $.trim($("#projectstage").val());
		var replytime_s = $.trim($("#replytime_s").val());
		var replytime_e = $.trim($("#replytime_e").val());
		
		jQuery("#grid").jqGrid("setGridParam", {
			postData : {
				projectname : projectname,
				planyear : planyear,
				cityleaders : cityleaders,
				managementoffice : managementoffice,
				responsibilityunit : responsibilityunit,
				insteadunit : insteadunit,
				localgoverment : localgoverment,
				projecttype : projecttype,
				constructionnature : constructionnature,
				projectstage : projectstage,
				replytime_s : replytime_s,
				replytime_e : replytime_e
			}
		});
		jQuery("#grid").trigger("reloadGrid");
	});
	// 初始化表格
	$.fn.myDataGrid({
		// 列表数据请求路径
		gridUrl : path + '/business/queryProject.ajax',
		// 表格渲染table ID
		gridId : 'grid',
		// 分页渲染DIV ID
		pagerId : 'page',
		// 高度
		height : 300,
		// 表头
		colNames : [ 'PROJECTID', '项目名称', '年度大计划', '分管市领导', '责任处室', '责任单位', '代建单位', '辖区政府', '项目类别',
		             '建设性质', '建设阶段', '总投资', '已下达资金', '操作' ],
		colModel : [ {
			name : 'PROJECTID',
			index : 'PROJECTID',
			sortable : false,
			hidden : true
		}, {
			name : 'PROJECTNAME',
			index : 'PROJECTNAME',
			sortable : false
		}, {
			name : 'PLANYEAR',
			index : 'PLANYEAR',
			sortable : false
		}, {
			name : 'CITYLEADERS',
			index : 'CITYLEADERS',
			sortable : false
		}, {
			name : 'MANAGEMENTOFFICE_NAME',
			index : 'MANAGEMENTOFFICE_NAME',
			sortable : false
		}, {
			name : 'RESPONSIBILITYUNIT',
			index : 'RESPONSIBILITYUNIT',
			sortable : false
		}, {
			name : 'INSTEADUNIT',
			index : 'INSTEADUNIT',
			sortable : false
		}, {
			name : 'LOCALGOVERMENT_NAME',
			index : 'LOCALGOVERMENT_NAME',
			sortable : false
		}, {
			name : 'PROJECTTYPE_NAME',
			index : 'PROJECTTYPE_NAME',
			sortable : false
		}, {
			name : 'CONSTRUCTIONNATURE_NAME',
			index : 'CONSTRUCTIONNATURE_NAME',
			sortable : false
		}, {
			name : 'PROJECTSTAGE_NAME',
			index : 'PROJECTSTAGE_NAME',
			sortable : false
		}, {
			name : 'TOTALINVESTMENT',
			index : 'TOTALINVESTMENT',
			sortable : true
		}, {
			name : 'ACTUALINVEST',
			index : 'ACTUALINVEST',
			sortable : true
		},{
   			name:'operate',
   			sortable:false,
   			align:'center',
   			width: 100,
   			formatter:operate
	   	}]
	});
	
	jQuery("#grid").jqGrid("setGridParam", {
		ondblClickRow: function(rowid){
			showInfo(rowid);
		}
	});

});

// 年度大计划显示
//function isyearplanOperate(cellvalue, options, rowObject){
//	var str = "";
//	if(rowObject.ISYEARPLAN){
//		if(rowObject.ISYEARPLAN=='1'&&rowObject.PLANYEAR){
//			str+=rowObject.ISYEARPLAN_NAME+"【"+rowObject.PLANYEAR+"】";
//		}else{
//			str+=rowObject.ISYEARPLAN_NAME;
//		}
//	}
//	
//	return str;
//}

// 列表项目类别显示
//function projecttypeOperate(cellvalue, options, rowObject){
//	var str = "";
//	if(rowObject.PROJECTTYPE){
//		if(rowObject.PROJECTTYPE2){
//			str+=rowObject.PROJECTTYPE_NAME+"【"+rowObject.PROJECTTYPE2_NAME+"】";
//		}else{
//			str+=rowObject.PROJECTTYPE_NAME;
//		}
//	}
//	
//	return str;
//}

//function projecttypeChange(projecttype){
//	var str = '<option value="">---请选择---</option>';
//	if(projecttype){
//		myAjaxReqJson(path+'/comm/getTreeDataByPid.ajax', function(data){
//			var list = data.list;
//			for(var  i = 0; i < list.length; i++){
//				str+='<option value="'+list[i].ID+'">'+list[i].NAME+'</option>';
//			}
//		}, {name: 'projecttype', pid: projecttype}, false);
//	}
//	$("#projecttype2").html(str);
//}

// 操作
function operate(cellvalue, options, rowObject) {
//	return "<a href='javascript:;' onclick='showInfo(\"" + rowObject.PROJECTID
//			+ "\")' class='oprateCss'>查看详细</a>"+
//			"&nbsp;&nbsp;<a href='javascript:;' onclick='showAnalyse(\"" + rowObject.PROJECTID
//			+ "\")' class='oprateCss'>分析</a>";
	return "<a href='javascript:;' onclick='showAnalyse(\"" + rowObject.PROJECTID
	+ "\")' class='oprateCss'>分析</a>";
}

function showInfo(PROJECTID) {
	window.location.href = path + '/business/addProjectPage.do?projectid='+PROJECTID+'&SEARCH_FLAG=SEARCH';
}

function showAnalyse(PROJECTID) {
	addUpdDialog({
		htmlPath: path+'/business/getAnalysePageById.do', 
		title: "项目分析",
		diaWeight: 920,
		args: {PROJECTID: PROJECTID}
	});
}
