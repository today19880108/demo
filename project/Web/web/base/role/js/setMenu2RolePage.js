myAjaxReqJson(path+'/base/getMenu2RoleTreeData.ajax', function(data){
	var setting = {
			view: {
				dblClickExpand: true
			},
			check:  {
				enable: true,
				chkStyle: "checkbox"
			},
			data: {
				simpleData: {
					enable: true,
					idKey: "ID",
					pIdKey: "PID",
					rootPId: "0"
				},
				key: {
					checked: "CHECKED",
					name: "NAME",
					title: "REMARK"
				}
			},
			callback: {
				
			}
		};
	var tree = $.fn.zTree.init($("#tree"), setting, data.list);
	// 展开树菜单所有节点
	tree.expandAll(true, true, true, false);
	
}, {ROLE_ID: $("#ROLE_ID").val()}, true);