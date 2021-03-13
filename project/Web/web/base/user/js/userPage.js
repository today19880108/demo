$(function(){
	$("#add").button();
	$("#add").bind("click", function(){
		addUpdDialog({
			htmlPath: path+'/base/getUserAddUpdPage.do', 
			title: "新增用户", 
			saveFun: function(){
				if(!($("#userAddUdpForm").myValidate())){
					return false;
				}
				//dialog中的form提交不了
				//$("#userAddUdpForm").submit(function(){$(this).ajaxSubmit({})});
				myAjaxReqJson(path+'/base/saveUserAddUdp.do', function(result, textStatus){
					if(result.only=="yes"){
				    	 myDialog("保存成功！", 1);
	    	 			 $("#consoleId").dialog("close");
	    	 			 $("#grid").trigger("reloadGrid");
					}else{
						myDialog("该用户登录名已被使用！", 0);
					}
			   }, $("#userAddUdpForm").getFormData(), false);
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
			htmlPath: path+'/base/getUserAddUpdPage.do', 
			title: "修改用户", 
			args: {USER_ID: selectedRowId[0]},
			saveFun: function(){
				if(!($("#userAddUdpForm").myValidate())){
					return false;
				}
				myAjaxReqJson(path+'/base/saveUserAddUdp.do', function(result, textStatus){
					if(result.only=="yes"){
				    	 myDialog("保存成功！", 1);
	    	 			 $("#consoleId").dialog("close");
	    	 			 $("#grid").trigger("reloadGrid");
					}else{
						myDialog("该用户登录名已被使用！", 0);
					}
			   }, $("#userAddUdpForm").getFormData(), false);
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
				myAjaxReqJson(path+'/base/delUser.do', function(result, textStatus){
					myDialog("删除成功！", 1);
					$("#grid").trigger("reloadGrid");
			   }, {USER_ID: selectedRowId}, false);
			});
		}
	});
	$("#search").button();
	$("#search").bind("click", function(){
		var USER_NAME = $.trim($("#USER_NAME").val());
		var DEPT = $.trim($("#DEPT").val());
		var USER_CODE = $.trim($("#USER_CODE").val());
		var TEL = $.trim($("#TEL").val());
		var ISUSER = $.trim($("#ISUSER").val());
		jQuery("#grid").jqGrid("setGridParam", {
			postData: {
				USER_NAME: USER_NAME,
				DEPT: DEPT,
				USER_CODE: USER_CODE,
				TEL: TEL,
				ISUSER: ISUSER
			} 
		}); 
		jQuery("#grid").trigger("reloadGrid");
	});
	// 初始化部门选择框
	myAjaxReqJson(path+'/comm/getTreeData.ajax', function(data){
		initSelectZtree({
			zNodes: data.list, 
			clickId: "DEPT_NAME", 
			event: "focus", 
			showObjId: "DEPT_NAME", 
			valObjId: "DEPT"
		});
	}, {name: 'user_dept'}, true);
	// 判断清除部门事件
	$("#DEPT_NAME").bind("keyup", function(e){
		if (e.keyCode==8&&$.trim($("#DEPT_NAME").val()).length==0){
			$("#DEPT").val("");
			$("#DEPT_NAME").val("");
		}
	});
	// 初始化表格
	$.fn.myDataGrid({
		// 列表数据请求路径
		gridUrl: path+'/base/getUserList.ajax',         
		// 表格渲染table ID
		gridId: 'grid',
		// 分页渲染DIV ID
		pagerId: 'page',
		// 表头
		colNames: ['USER_ID', '用户名称', '用户登录名', '所属部门', '身份证号', '性别', '年龄', '联系电话', '邮箱', '描述', '显示顺序', '状态', '操作'],
		colModel: [{								
			name:'USER_ID',
			index:'USER_ID',
			sortable:false,
			hidden:true
	   		},{
	   		name:'USER_NAME',					
	   		index:'USER_NAME',	
	   		sortable:true
	   		},{
	   		name:'USER_LOGIN',					
	   		index:'USER_LOGIN',	
	   		sortable:true
	   		},{
   		 	name:'DEPT_NAME',
   		 	index:'DEPT_NAME', 
   		 	sortable:true
	   		},{
   		 	name:'USER_CODE',
   		 	index:'USER_CODE', 
   		 	sortable:true
	   		},{
   		 	name:'SEX_NAME',
   		 	index:'SEX_NAME', 
   		 	sortable:true
	   		},{
   		 	name:'AGE',
   		 	index:'AGE', 
   		 	sortable:true
	   		},{
   		 	name:'TEL',
   		 	index:'TEL', 
   		 	sortable:true
	   		},{
   		 	name:'EMAIL',
   		 	index:'EMAIL', 
   		 	sortable:true
	   		},{
   		 	name:'REMARK',
   		 	index:'REMARK', 
   		 	sortable:false
	   		},{
   		 	name:'SHOW_ORDER',
   		 	index:'SHOW_ORDER', 
   		 	sortable:true
	   		},{
   		 	name:'ISUSER_NAME',
   		 	index:'ISUSER_NAME', 
   		 	sortable:true
	   		},{
   			name:'operate',
   			sortable:false,
   			align:'center',
   			width:300,
   			formatter:operate
	   		}
   		]
	});
});

// 操作
function operate(cellvalue, options, rowObject){
	return "<a href='javascript:;' onclick='showInfo(\""+rowObject.USER_ID+"\")' class='oprateCss'>查看详细</a>"
		+"&nbsp;&nbsp;<a href='javascript:;' onclick='setRole2User(\""+rowObject.USER_ID+"\")' class='oprateCss'>分配角色</a>";
}

function showInfo(USER_ID){
	addUpdDialog({
		htmlPath: path+'/base/getUserAddUpdPage.do', 
		title: "查看用户", 
		args: {USER_ID: USER_ID, SEARCH_FLAG: "SEARCH"}
	});
}

function setRole2User(USER_ID){
	addUpdDialog({
		htmlPath: path+'/base/getSetRole2UserPage.do', 
		title: "分配角色", 
		args: {USER_ID: USER_ID},
		diaWeight: 800
	});
}

