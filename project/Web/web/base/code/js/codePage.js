$(function(){
	$("#add").button();
	$("#add").bind("click", function(){
		addUpdDialog({
			htmlPath: path+'/base/getCodeAddUpdPage.do', 
			title: "新增字典", 
			saveFun: function(){
				if(!($("#codeAddUdpForm").myValidate())){
					return false;
				}
				myAjaxReqJson(path+'/base/saveCodeAddUdp.do', function(result, textStatus){
			   	 	if(result.only=="yes"){
					    myDialog("保存成功！", 1);
			   	 		$("#consoleId").dialog("close");
			   	 		$("#grid").trigger("reloadGrid");
					}else{
						myDialog("该字典类型的字典标识已被使用！", 0);
					}
			    }, $("#codeAddUdpForm").getFormData(), false);
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
			htmlPath: path+'/base/getCodeAddUpdPage.do', 
			title: "修改字典", 
			args: {CODE_ID: selectedRowId[0]},
			saveFun: function(){
				if(!($("#codeAddUdpForm").myValidate())){
					return false;
				}
				myAjaxReqJson(path+'/base/saveCodeAddUdp.do', function(result, textStatus){
					if(result.only=="yes"){
					    myDialog("保存成功！", 1);
			   	 		$("#consoleId").dialog("close");
			   	 		$("#grid").trigger("reloadGrid");
					}else{
						myDialog("该字典类型的字典标识已被使用！", 0);
					}
			    }, $("#codeAddUdpForm").getFormData(), false);
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
				myAjaxReqJson(path+'/base/delCode.do', function(result, textStatus){
					myDialog("删除成功！", 1);
					$("#grid").trigger("reloadGrid");
			   }, {CODE_ID: selectedRowId}, false);
			});
		}
	});
	$("#search").button();
	$("#search").bind("click", function(){
		var NAME = $.trim($("#NAME").val());
		var TYPE = $.trim($("#TYPE").val());
		jQuery("#grid").jqGrid("setGridParam", {
			postData: {
				NAME: NAME,
				TYPE: TYPE
			} 
		}); 
		jQuery("#grid").trigger("reloadGrid");
	});
	// 初始化表格
	$.fn.myDataGrid({
		// 列表数据请求路径
		gridUrl: path+'/base/getCodeList.ajax',         
		// 表格渲染table ID
		gridId: 'grid',
		// 分页渲染DIV ID
		pagerId: 'page',
		// 表头
		colNames: ['CODE_ID', '字典标识', '字典名称', '字典类型', '显示顺序', '操作'],
		colModel: [{								
			name:'CODE_ID',
			index:'CODE_ID',
			sortable:false,
			hidden:true
			},{
	   		name:'ID',					
	   		index:'ID',	
	   		sortable:true
	   		},{
   		 	name:'NAME',
   		 	index:'NAME', 
   		 	sortable:true
	   		},{
   		 	name:'TYPE_NAME',
   		 	index:'TYPE_NAME', 
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

//操作
function operate(cellvalue, options, rowObject){
	return "<a href='javascript:;' onclick='showInfo(\""+rowObject.CODE_ID+"\")' class='oprateCss'>查看详细</a>";
}

function showInfo(CODE_ID){
	addUpdDialog({
		htmlPath: path+'/base/getCodeAddUpdPage.do', 
		title: "查看字典", 
		args: {CODE_ID: CODE_ID, SEARCH_FLAG: "SEARCH"}
	});
}
