$(document).ready(function(){
	myAjaxReqJson(path+'/base/getSysMenuTreeData.ajax', function(data){
		var setting = {
			view: {
				showLine: true,
				selectedMulti: false,
				dblClickExpand: false
			},
			data: {
				simpleData: {
					enable: true,
					idKey: "ID",
					pIdKey: "PID",
					rootPId: "0"
				},
				key: {
					name: "NAME",
					title: "REMARK"
				}
			},
			callback: {
				onClick: treeNodeClick
			}
		};

		var tree = $.fn.zTree.init($("#tree1"), setting, data.list);
		// 展开树菜单第一个节点及其子节点
		//tree.expandNode(tree.getNodes()[0], true, true, true, false);
		// 展开树菜单所有节点
		tree.expandAll(true, true, true, false);
	}, {}, true);
	
	// 修改密码
	$("#setPwd").bind("click", function(){
		addUpdDialog({
			htmlPath: path+'/base/getSetPwdPage.do', 
			title: "修改密码", 
			diaWeight: 500,
			position: ["center", 100],
			saveFun: function(){
				if(!($("#setPwdForm").myValidate())){
					return false;
				}
				if($("#NEW_USER_PWD").val()!=$("#NEW2_USER_PWD").val()){
					myDialog("您两次输入的新密码不一样！", 0);
					return false;
				}
				myAjaxReqJson(path+'/base/saveSetPwd.do', function(result, textStatus){
					myDialog("修改成功！", 1);
		   	 		$("#consoleId").dialog("close");
			    }, $("#setPwdForm").getFormData(), false);
			}
		});
	});
	
	// 注销
    $("#signOut").bind("click", function(){
		myDialogConfirm("您确定退出系统？", function(){
			$("#signOut_form").attr("action", path+"/welcome/signOut.do");
			$("#signOut_form").submit();
		});
	});
});

function treeNodeClick(event, treeId, treeNode, clickFlag){
	var treeObj = $.fn.zTree.getZTreeObj(treeId);
	if(treeNode.isParent){
		treeObj.expandNode(treeNode, !treeNode.open, false, true, false);
	}else if(treeNode.PATH){
		var str = "";
		var nowObj = treeNode;
		var nowPid = nowObj.PID;
		var flag;
		do{
			str = " 》"+nowObj.NAME+str;
			flag = false;
			if(nowPid!=0){
				nowObj = treeObj.getNodeByParam("ID", nowObj.PID, null);
				nowPid = nowObj.PID;
				flag = true;
			}
		} while(flag)
		str = "业务内容" + str;
		$("#titleSpan").html(str);
			
		$("#main_content").html('<iframe src="'+path+treeNode.PATH+'" frameborder="0" style="width:100%;height:100%;"></iframe>');
	}
}
