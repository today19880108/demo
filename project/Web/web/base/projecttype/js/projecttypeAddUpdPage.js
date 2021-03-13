if($("#SEARCH_FLAG").val()!="SEARCH"){
	// 初始化部门选择框
	myAjaxReqJson(path+'/comm/getTreeData.ajax', function(data){
		initSelectZtree({
			zNodes: data.list, 
			clickId: "SELECT_BUT", 
			event: "click", 
			showObjId: "PID_NAME2", 
			valObjId: "PID2",
			zindex: 5000
		});
	}, {name: "projecttype"}, true);
	// 判断清除部门事件
	$("#PID_NAME2").bind("keyup", function(e){
		if (e.keyCode==8&&$.trim($("#PID_NAME2").val()).length==0){
			$("#PID2").val("");
			$("#PID_NAME2").val("");
		}
	});
}else{
	$("#SELECT_BUT").hide();
	setReadOnly("menuAddUdpForm");
}
