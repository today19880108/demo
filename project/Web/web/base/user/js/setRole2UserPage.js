$("#add2").button({
	icons: {primary: "ui-icon-carat-1-s"}
});
$("#add2").bind("click", function(){
	var selectedRowId = $("#grid2").jqGrid("getGridParam", "selarrrow");
	if (!selectedRowId || selectedRowId.length==0) {
		myDialog("请选择需要添加的角色!", 0);  
        return false;
	}else{
		myAjaxReqJson(path+'/base/oprateRole2User.do', function(result, textStatus){
			myDialog("添加成功！", 1);
			$("#grid3").trigger("reloadGrid");
	   }, {USER_ID: $("#USER_ID").val(), ROLE_ID: selectedRowId, oprate: "1"}, false);
	}
});

$("#del2").button({
	icons: {primary: "ui-icon-carat-1-n"}
});
$("#del2").bind("click", function(){
	var selectedRowId = $("#grid3").jqGrid("getGridParam", "selarrrow");
	if (!selectedRowId || selectedRowId.length==0) {
		myDialog("请选择需要删除的角色!", 0);  
        return false;
	}else{
		myDialogConfirm("您确定要删除选中的角色嘛？", function(){
			myAjaxReqJson(path+'/base/oprateRole2User.do', function(result, textStatus){
				myDialog("删除成功！", 1);
				$("#grid3").trigger("reloadGrid");
		   }, {USER_ID: $("#USER_ID").val(), ROLE_ID: selectedRowId, oprate: "3"}, false);
		});
	}
});
// 初始化表格
$.fn.myDataGrid({
	// 列表数据请求路径
	gridUrl: path+'/base/getRoleList.ajax',         
	// 表格渲染table ID
	gridId: 'grid2',
	// 分页渲染DIV ID
	pagerId: 'page2',
	height: 100,
	// 表头
	colNames: ['ID', '角色名称', '角色描述', '操作'],
	colModel: [{								
		name:'ID',
		index:'ID',
		sortable:false,
		hidden:true
		},{
   		name:'NAME',					
   		index:'NAME',	
   		sortable:true
   		},{
	 	name:'REMARK',
	 	index:'REMARK', 
	 	sortable:true
   		},{
		name:'operate',
		sortable:false,
		align:'center',
		formatter:operate
   		}
	]
});
// 初始化表格
$.fn.myDataGrid({
	// 列表数据请求路径
	gridUrl: path+'/base/getRoleListByUserID.ajax',         
	// 表格渲染table ID
	gridId: 'grid3',
	// 分页渲染DIV ID
	pagerId: 'page3',
	postData: {
		USER_ID: $("#USER_ID").val()
	},
	height: 100,
	// 表头
	colNames: ['ID', '角色名称', '角色描述', '操作'],
	colModel: [{								
		name:'ID',
		index:'ID',
		sortable:false,
		hidden:true
		},{
   		name:'NAME',					
   		index:'NAME',	
   		sortable:true
   		},{
	 	name:'REMARK',
	 	index:'REMARK', 
	 	sortable:true
   		},{
		name:'operate',
		sortable:false,
		align:'center',
		formatter:operate
   		}
	]
});

//操作
function operate(cellvalue, options, rowObject){
	return "<a href='javascript:;' onclick='setMenu2Role(\""+rowObject.ID+"\")' class='oprateCss'>查看权限</a>";
}

function setMenu2Role(ID){
	addUpdDialog({
		consoleId: "consoleId2",
		htmlPath: path+'/base/getSetMenu2RolePage.do', 
		title: "查看权限", 
		args: {ID: ID},
		diaWeight: 300
	});
}