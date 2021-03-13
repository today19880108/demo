$(function(){
	$("#add").button();
	$("#add").bind("click", function(){
		addUpdDialog({
			htmlPath: path+'/base/addProjecttypePage.do', 
			title: "新增部门", 
			saveFun: function(){
				if(!($("#menuAddUdpForm").myValidate())){
					return false;
				}
				myAjaxReqJson(path+'/base/saveProjecttype.do', function(result, textStatus){
					myDialog("保存成功！", 1);
		   	 		$("#consoleId").dialog("close");
		   	 		$("#grid").trigger("reloadGrid");
			    }, $("#menuAddUdpForm").getFormData(), false);
			}
		});
	});
	$("#upd").button();
	$("#upd").bind("click", function(){
		var selectedRowId = $("#grid").jqGrid("getGridParam", "selarrrow");
		if (!selectedRowId || selectedRowId.length==0) {
			myDialog("请选择需要修改的记录!", 0);  
	        return false;
		}else if(selectedRowId.length > 1){
			myDialog("您选择修改的记录过多!", 0);  
	        return false;
		}
		addUpdDialog({
			htmlPath: path+'/base/addProjecttypePage.do', 
			title: "修改部门", 
			args: {ID: selectedRowId[0]},
			saveFun: function(){
				if(!($("#menuAddUdpForm").myValidate())){
					return false;
				}
				myAjaxReqJson(path+'/base/saveProjecttype.do', function(result, textStatus){
					myDialog("保存成功！", 1);
		   	 		$("#consoleId").dialog("close");
		   	 		$("#grid").trigger("reloadGrid");
			    }, $("#menuAddUdpForm").getFormData(), false);
			}
		});
	});
	$("#del").button();
	$("#del").bind("click", function(){
		var selectedRowId = $("#grid").jqGrid("getGridParam", "selarrrow");
		if (!selectedRowId || selectedRowId.length==0) {
			myDialog("请选择需要删除的记录!", 0);  
	        return false;
		}else{
			myDialogConfirm("您确定要删除选中的记录嘛？", function(){
				myAjaxReqJson(path+'/base/delProjecttype.do', function(result, textStatus){
					myDialog("删除成功！", 1);
					$("#grid").trigger("reloadGrid");
			   }, {ID: selectedRowId}, false);
			});
		}
	});
	$("#search").button();
	$("#search").bind("click", function(){
		var PID = $.trim($("#PID").val());
		var NAME = $.trim($("#NAME").val());
		jQuery("#grid").jqGrid("setGridParam", {
			postData: {
				pid: PID,
				name: NAME
			} 
		}); 
		jQuery("#grid").trigger("reloadGrid");
	});
	// 初始化上级部门选择框
	myAjaxReqJson(path+'/comm/getTreeData.ajax', function(data){
		initSelectZtree({
			zNodes: data.list, 
			clickId: "PID_NAME", 
			event: "focus", 
			showObjId: "PID_NAME", 
			valObjId: "PID",
			zindex: 5000
		});
	}, {name: "projecttype"}, true);
	// 判断清除上级部门事件
	$("#PID_NAME").bind("keyup", function(e){
		if (e.keyCode==8&&$.trim($("#PID_NAME").val()).length==0){
			$("#PID").val("");
			$("#PID_NAME").val("");
		}
	});
	// 初始化表格
	$.fn.myDataGrid({
		// 列表数据请求路径
		gridUrl: path+'/base/getProjecttypeList.ajax',         
		// 表格渲染table ID
		gridId: 'grid',
		// 分页渲染DIV ID
		pagerId: 'page',
		// 表头
		colNames: ['ID', '上级项目类别', '项目类别名称', '项目类别描述', '显示顺序', '操作'],
		colModel: [{								
			name:'ID',
			index:'ID',
			sortable:false,
			hidden:true
			},{
	   		name:'PID_NAME',					
	   		index:'PID_NAME',	
	   		sortable:true
	   		},{
	   		name:'NAME',					
	   		index:'NAME',	
	   		sortable:true
	   		},{
   		 	name:'REMARK',
   		 	index:'REMARK', 
   		 	sortable:true
	   		},{
   		 	name:'ORD',
   		 	index:'ORD', 
   		 	sortable:true
	   		},{
   			name:'operate',
   			sortable:false,
   			align:'center',
   			formatter:operate
	   		}
   		]
	});
});

// 操作
function operate(cellvalue, options, rowObject){
	return "<a href='javascript:;' onclick='showInfo(\""+rowObject.ID+"\")' class='oprateCss'>查看详细</a>";
}

function showInfo(ID){
	addUpdDialog({
		htmlPath: path+'/base/addProjecttypePage.do', 
		title: "查看部门", 
		args: {ID: ID, SEARCH_FLAG: "SEARCH"}
	});
}


