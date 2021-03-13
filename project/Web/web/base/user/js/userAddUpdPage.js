if($("#SEARCH_FLAG").val()!="SEARCH"){
	// 初始化部门选择框
	myAjaxReqJson(path+'/comm/getTreeData.ajax', function(data){
		initSelectZtree({
			zNodes: data.list, 
			clickId: "DEPT_SELECT_BUT", 
			event: "click", 
			showObjId: "DEPT_NAME2", 
			valObjId: "DEPT2",
			zindex: 5000
		});
	}, {name: 'user_dept'}, true);
	// 判断清除部门事件
	$("#DEPT_NAME2").bind("keyup", function(e){
		if (e.keyCode==8&&$.trim($("#DEPT_NAME2").val()).length==0){
			$("#DEPT2").val("");
			$("#DEPT_NAME2").val("");
		}
	});
}else{
	$("#DEPT_SELECT_BUT").hide();
	setReadOnly("userAddUdpForm");
}