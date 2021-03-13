$(function(){
	$("#add").button();
	$("#add").bind("click", function(){
		addUpdDialog({
			htmlPath: path+'/base/getRoleAddUpdPage.do', 
			title: "新增角色", 
			saveFun: function(){
				if(!($("#roleAddUdpForm").myValidate())){
					return false;
				}
				myAjaxReqJson(path+'/base/saveRoleAddUdp.do', function(result, textStatus){
					myDialog("保存成功！", 1);
		   	 		$("#consoleId").dialog("close");
		   	 		$("#grid").trigger("reloadGrid");
			    }, $("#roleAddUdpForm").getFormData(), false);
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
			htmlPath: path+'/base/getRoleAddUpdPage.do', 
			title: "修改角色", 
			args: {ID: selectedRowId[0]},
			saveFun: function(){
				if(!($("#roleAddUdpForm").myValidate())){
					return false;
				}
				myAjaxReqJson(path+'/base/saveRoleAddUdp.do', function(result, textStatus){
					myDialog("保存成功！", 1);
		   	 		$("#consoleId").dialog("close");
		   	 		$("#grid").trigger("reloadGrid");
			    }, $("#roleAddUdpForm").getFormData(), false);
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
				myAjaxReqJson(path+'/base/delRole.do', function(result, textStatus){
					myDialog("删除成功！", 1);
					$("#grid").trigger("reloadGrid");
			   }, {ID: selectedRowId}, false);
			});
		}
	});
	$("#search").button();
	$("#search").bind("click", function(){
		var NAME = $.trim($("#NAME").val());
		var REMARK = $.trim($("#REMARK").val());
		jQuery("#grid").jqGrid("setGridParam", {
			postData: {
				NAME: NAME,
				REMARK: REMARK
			} 
		}); 
		jQuery("#grid").trigger("reloadGrid");
	});
	// 初始化表格
	$.fn.myDataGrid({
		// 列表数据请求路径
		gridUrl: path+'/base/getRoleList.ajax',         
		// 表格渲染table ID
		gridId: 'grid',
		// 分页渲染DIV ID
		pagerId: 'page',
		// 表头
		colNames: ['ID', '角色名称', '角色描述', '显示顺序', '操作'],
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
	return "<a href='javascript:;' onclick='showInfo(\""+rowObject.ID+"\")' class='oprateCss'>查看详细</a>"
		+ "&nbsp;&nbsp;<a href='javascript:;' onclick='setMenu2Role(\""+rowObject.ID+"\")' class='oprateCss'>分配权限</a>";
}

function showInfo(ID){
	addUpdDialog({
		htmlPath: path+'/base/getRoleAddUpdPage.do', 
		title: "查看角色", 
		args: {ID: ID, SEARCH_FLAG: "SEARCH"}
	});
}

function setMenu2Role(ID){
	addUpdDialog({
		htmlPath: path+'/base/getSetMenu2RolePage.do', 
		title: "分配权限", 
		args: {ID: ID},
		diaWeight: 300,
		saveFun: function(){
			var treeObj = $.fn.zTree.getZTreeObj("tree");
			var nodes = treeObj.getCheckedNodes(true);
			var MENU_ID = "";
			for(var i = 0; i < nodes.length; i++){
				MENU_ID+=nodes[i].ID+",";
			}
			if(MENU_ID.length > 0){
				MENU_ID = MENU_ID.substring(0, MENU_ID.length-1);
			}
			myAjaxReqJson(path+'/base/saveMenu2Role.ajax', function(data){
				myDialog("保存成功！", 1);
			}, {ROLE_ID: $("#ROLE_ID").val(), MENU_ID: MENU_ID}, true);
		}
	});
}


