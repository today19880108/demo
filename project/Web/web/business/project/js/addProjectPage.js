$(function(){
	if($("#SEARCH_FLAG").val()!='SEARCH'){
		// 年度大计划
//		var year = $("#now_year").val()*1;
//		var optionStr = "";
//		for(var i = year; i>=2000; i--){
//			optionStr+='<option value="'+i+'" '+(i==year?"selected":"")+'>'+i+'</option>';
//		}
//		$("#planyear").html(optionStr);
//		$("#planyear").val($("#now_planyear").val());
//		if($("#SEARCH_FLAG").val()!='SEARCH'){
//			$("#isyearplan").bind("change", function(){
//				if($(this).val()==1){
//					$("#planyear").show();
//					$("#planyear").next().show();
//				}else{
//					$("#planyear").hide();
//					$("#planyear").next().hide();
//				}
//			});
//		}
		var $mselectObj = $("#isyearplan");
	    $mselectObj.multiselect({
	    	header: false,
	    	classes: 'myMultiselectClass',
	        noneSelectedText: "---请选择---",
	        //checkAllText: "全选",
	        //uncheckAllText: '全不选',
	        selectedList: $mselectObj.find('option').length     
	    });
	    $mselectObj.on("multiselectclick", function(event, ui){
	    	var isFalse = false;
	    	var $getChecked = $mselectObj.multiselect("getChecked");
			$getChecked.each(function(i){
				if($(this).val()=="否"){
					isFalse = true;
					return false;
				}
			});
			if(isFalse){
				$getChecked.each(function(i){
					if((ui.value=="否"&&$(this).val()!="否")||(ui.value!="否"&&$(this).val()=="否")){
						$(this).removeAttr("checked");
					}
				});
			}
			$getChecked = $mselectObj.multiselect("getChecked");
			var v = "";
			$getChecked.each(function(i){
				v+=$(this).val()+",";
			});
			if(v.length > 0){
				v = v.substring(0, v.length-1);
			}
			$("#planyear").val(v);
	    });
	    
	    // 辖区政府
	    var $selectLocalgoverment = $("#selectLocalgoverment");
	    $selectLocalgoverment.multiselect({
	    	header: false,
	    	classes: 'myMultiselectClass',
	        noneSelectedText: "---请选择---",
	        //checkAllText: "全选",
	        //uncheckAllText: '全不选',
	        selectedList: $selectLocalgoverment.find('option').length     
	    });
	    $selectLocalgoverment.on("multiselectclick", function(event, ui){
	    	var $getChecked = $selectLocalgoverment.multiselect("getChecked");
	    	var v = "";
			$getChecked.each(function(i){
				v+=$(this).val()+",";
			});
			if(v.length > 0){
				v = v.substring(0, v.length-1);
			}
			$("#localgoverment").val(v);
	    });
	    
	    // 分管市领导自动提示
	    myAjaxReqJson(path+'/comm/getCodeInAllTable.do', function(result, textStatus){
			var data = result.list;
			var str = '<div id="cityleadersSelectPanel" style="position: absolute; width: '+$("#cityleaders").outerWidth()+'px; top: '+($("#cityleaders").offset().top+1+$("#cityleaders").outerHeight())+'px; left: '+$("#cityleaders").offset().left+'px; display: none;">'
						+'<ul style="padding: 3px 5px 3px 3px;max-height: 125px; overflow: auto; background-color: #fff; border: 1px solid #bbb;">';
			for(var i = 0; i < data.length; i++){
				str+='<li style="height:22px; line-height:22px;" class="ui-corner-all'+(i==0?' ui-state-hover':'')+'">'+data[i].NAME+'</li>';
			}
			str+='</ul></div>';
	    	$("body").append(str);
	    	
	    	$("#cityleadersSelectPanel ul li").hover(
		    	function(){
		    		$("#cityleadersSelectPanel ul li").removeClass("ui-state-hover");
		    		$(this).addClass("ui-state-hover");
		    	},
		    	function(){
		    		
		    	}
		    );
	    	$("#cityleadersSelectPanel ul li").click(
	    	    function(){
	    	    	$("#cityleaders").val($(this).text());
	    	    }
	    	);
	    	$(document).bind("click", function(e){
	    		if(e.target===$("#cityleaders")[0]){
	    			if($("#cityleadersSelectPanel").is(":hidden")){
	    				$("#cityleadersSelectPanel").slideDown(100);
	    			}
	    		}else if(!$.contains($("#cityleadersSelectPanel")[0], e.target)){
	    			$("#cityleadersSelectPanel").slideUp(100);
	    		}
	    	});
	    }, {code_type:'9'}, true);
	    
		// 项目类别
//		if($("#projecttype").val()){
//			projecttypeChange($("#projecttype").val(), $("#now_projecttype2").val());
//		}
//		$("#projecttype").bind("change", function(){
//			projecttypeChange($(this).val());
//		});
	    // 项目类别
	    myAjaxReqJson(path+'/comm/getTreeData.ajax', function(data){
			initSelectZtree({
				zNodes: data.list, 
				clickId: "projecttypeTree", 
				event: "click", 
				showObjId: "projecttypeTree", 
				valObjId: "projecttype",
				howShow: "firts",
				zindex: 5000
			});
		}, {name: "projecttype"}, true);
		$("#projecttypeTree").bind("keyup", function(e){
			if (e.keyCode==8&&$.trim($("#projecttypeTree").val()).length==0){
				$("#projecttype").val("");
				$("#projecttypeTree").val("");
			}
		});
		// 建设期限
		jQuery('#addProjectForm #starttime').datepicker({
			changeMonth: true, 
		    changeYear: true, 
		    dateFormat: "yy-mm-dd"
		});
		jQuery('#addProjectForm #endtime').datepicker({
			changeMonth: true, 
		    changeYear: true, 
		    dateFormat: "yy-mm-dd"
		});
	}else{
		setReadOnly("addProjectForm");
	}
	
//	$("#fileDown").bind("click", function(){
//		var url = path+'/business/downloadByFilePath.do?filepath='+encodeURI(encodeURI($("#fileDown_name").html()));
//		$("#addProjectForm").attr("method", "post");
//		$("#addProjectForm").attr("action", url);
//		$("#addProjectForm").submit();
//	});
	
	$("#save").button();
	$("#save").bind("click", function(){
		if (!($("#addProjectForm").myValidate())) {
			return false;
		}
		myAjaxReqJson(path + '/business/addProject.do',
			function(result, textStatus) {
				if (!result.projectid) {
					myDialog("保存失败！", 0);
				}else{
//					if ($("#myFile").val().length > 0) {
//						$.ajaxFileUpload({
//							url : path
//									+ '/business/addAttach.do?projectid='
//									+ result.projectid, // submit to
//							// UploadFileServlet
//							secureuri : false,
//							fileElementId : 'myFile',
//							dataType : 'text', // or json xml whatever you
//							success : function(data, status) {
//								if (data=="1") {
//									myDialog("附件上传失败！", 0);
//								}else{
//									$("#fileDown_li").show();
//									$("#fileDown_li #fileDown_name").html(data);
//								}
//							},
//							error : function(data, status, e) {
//								myDialog(data, 0);
//							}
//						});
//					}
					
					$("#projectid").val(result.projectid);
					myDialog("保存成功！", 1);
				}
			}, $("#addProjectForm").getFormData(), false);
	});
	$("#return").button();
	$("#return").bind("click", function(){
		window.location.href = path + '/business/projectManagePage.do';
	});
	
	$("#add").button();
	$("#add").bind("click", function(){
		if($("#projectid").val().length==0){
			myDialog("请先保存项目信息！", 0);
			return;
		}
		addUpdDialog({
			htmlPath: path+'/business/addIvestmentPage.do', 
			title: "新增投资", 
			args: {projectid: $("#projectid").val()},
			saveFun: function(){
				if(!($("#addInvestForm").myValidate())){
					return false;
				}
				myAjaxReqJson(path+'/business/addInvest.do', function(result, textStatus){
					if(result.investmentid){
						if ($("#myFile3").val().length > 0) {
							$.ajaxFileUpload({
								url : path
										+ '/business/addInvestmentAttach.do?investmentid='
										+ result.investmentid, // submit to
								// UploadFileServlet
								secureuri : false,
								fileElementId : 'myFile3',
								dataType : 'text', // or json xml whatever you
								success : function(data, status) {
									if (data=="1") {
										myDialog("附件上传失败！", 0);
									}else{
//										$("#fileDown_li3").show();
//										$("#fileDown_li3 #fileDown_name3").html(data);
										
										myDialog("保存成功！", 1);
					    	 			$("#consoleId").dialog("close");
					    	 			jQuery("#grid").jqGrid("setGridParam", {
					    	 				postData : {
					    	 					projectid : $("#projectid").val()
					    	 				}
					    	 			});
					    	 			$("#grid").trigger("reloadGrid");
									}
								},
								error : function(data, status, e) {
									myDialog(data, 0);
								}
							});
						}else{
							myDialog("保存成功！", 1);
		    	 			$("#consoleId").dialog("close");
		    	 			jQuery("#grid").jqGrid("setGridParam", {
		    	 				postData : {
		    	 					projectid : $("#projectid").val()
		    	 				}
		    	 			});
		    	 			$("#grid").trigger("reloadGrid");
						}
					}else{
						myDialog("保存失败！", 0);
					}
			   }, $("#addInvestForm").getFormData(), false);
			}
		});
	});
	$("#upd").button();
	$("#upd").bind("click", function(){
		if($("#projectid").val().length==0){
			myDialog("请先保存项目信息！", 0);
			return;
		}
		var selectedRowId = $("#grid").jqGrid("getGridParam", "selarrrow");
		if (!selectedRowId || selectedRowId.length==0) {
			myDialog("请选择需要修改的记录!", 0);  
	        return false;
		}else if(selectedRowId.length > 1){
			myDialog("您选择修改的记录过多!", 0);  
	        return false;
		}
		addUpdDialog({
			htmlPath: path+'/business/addIvestmentPage.do', 
			title: "修改投资", 
			args: {projectid: $("#projectid").val(), investmentid: selectedRowId[0]},
			saveFun: function(){
				if(!($("#addInvestForm").myValidate())){
					return false;
				}
				myAjaxReqJson(path+'/business/addInvest.do', function(result, textStatus){
					if(result.investmentid){
						if ($("#myFile3").val().length > 0) {
							$.ajaxFileUpload({
								url : path
										+ '/business/addInvestmentAttach.do?investmentid='
										+ result.investmentid, // submit to
								// UploadFileServlet
								secureuri : false,
								fileElementId : 'myFile3',
								dataType : 'text', // or json xml whatever you
								success : function(data, status) {
									if (data=="1") {
										myDialog("附件上传失败！", 0);
									}else{
//										$("#fileDown_li3").show();
//										$("#fileDown_li3 #fileDown_name3").html(data);
										
										myDialog("保存成功！", 1);
					    	 			$("#consoleId").dialog("close");
					    	 			jQuery("#grid").jqGrid("setGridParam", {
					    	 				postData : {
					    	 					 projectid : $("#projectid").val()
					    	 				}
					    	 			});
					    	 			$("#grid").trigger("reloadGrid");
									}
								},
								error : function(data, status, e) {
									myDialog(data, 0);
								}
							});
						}else{
							myDialog("保存成功！", 1);
		    	 			$("#consoleId").dialog("close");
		    	 			jQuery("#grid").jqGrid("setGridParam", {
		    	 				postData : {
		    	 					 projectid : $("#projectid").val()
		    	 				}
		    	 			});
		    	 			$("#grid").trigger("reloadGrid");
						}
					}else{
						myDialog("保存失败！", 0);
					}
			   }, $("#addInvestForm").getFormData(), false);
			}
		});
	});
	$("#del").button();
	$("#del").bind("click", function(){
		if($("#projectid").val().length==0){
			myDialog("请先保存项目信息！", 0);
			return;
		}
		var selectedRowId = $("#grid").jqGrid("getGridParam", "selarrrow");
		if (!selectedRowId || selectedRowId.length==0) {
			myDialog("请选择需要删除的记录!", 0);  
	        return false;
		}else{
			myDialogConfirm("您确定要删除选中的记录嘛？", function(){
				myAjaxReqJson(path+'/business/delectInvestment.do', function(result, textStatus){
					myDialog("删除成功！", 1);
					jQuery("#grid").jqGrid("setGridParam", {
						postData : {
							projectid : $("#projectid").val()
						}
					});
					$("#grid").trigger("reloadGrid");
			   }, {investmentid: selectedRowId}, false);
			});
		}
	});
	$("#exportExcel").button();
	$("#exportExcel").bind("click", function(){
		if($("#projectid").val().length==0){
			myDialog("请先保存项目信息！", 0);
			return;
		}
		var url = path+'/business/exportInvest.do?projectid='+$("#projectid").val();
		window.location.href= url;
	});
	
	//项目阶段按钮事件
//	$("#add2").button();
//	$("#add2").bind("click", function(){
//		if($("#projectid").val().length==0){
//			myDialog("请先保存项目信息！", 0);
//			return;
//		}
//		addUpdDialog({
//			htmlPath: path+'/business/addStagePage.do', 
//			title: "新增项目阶段", 
//			args: {projectid: $("#projectid").val()},
//			saveFun: function(){
//				if(!($("#stageAddUdpForm").myValidate())){
//					return false;
//				}
//				myAjaxReqJson(path+'/business/saveStage.do', function(result, textStatus){
//					if(result.result=="s"){
//				    	 myDialog("保存成功！", 1);
//	    	 			 $("#consoleId").dialog("close");
//	    	 			 jQuery("#stagegrid").jqGrid("setGridParam", {
//	    	 				 postData : {
//	    	 					 projectid : $("#projectid").val()
//	    	 				}
//	    	 			 });
//	    	 			 $("#stagegrid").trigger("reloadGrid");
//					}else{
//						myDialog("保存失败！", 0);
//					}
//			    }, $("#stageAddUdpForm").getFormData(), false);
//			}
//		});
//	});
//	$("#upd2").button();
//	$("#upd2").bind("click", function(){
//		if($("#projectid").val().length==0){
//			myDialog("请先保存项目信息！", 0);
//			return;
//		}
//		var selectedRowId = $("#stagegrid").jqGrid("getGridParam", "selarrrow");
//		if (!selectedRowId || selectedRowId.length==0) {
//			myDialog("请选择需要修改的记录!", 0);  
//	        return false;
//		}else if(selectedRowId.length > 1){
//			myDialog("您选择修改的记录过多!", 0);  
//	        return false;
//		}
//		addUpdDialog({
//			htmlPath: path+'/business/addStagePage.do', 
//			title: "修改项目阶段", 
//			args: {projectid: $("#projectid").val(),stageid: selectedRowId[0]},
//			saveFun: function(){
//				if(!($("#stageAddUdpForm").myValidate())){
//					return false;
//				}
//				myAjaxReqJson(path+'/business/saveStage.do', function(result, textStatus){
//					if(result.result=="s"){
//				    	 myDialog("保存成功！", 1);
//	    	 			 $("#consoleId").dialog("close");
//	    	 			 jQuery("#stagegrid").jqGrid("setGridParam", {
//	    	 				 postData : {
//	    	 					 projectid : $("#projectid").val()
//	    	 				}
//	    	 			 });
//	    	 			 $("#stagegrid").trigger("reloadGrid");
//					}else{
//						myDialog("保存失败！", 0);
//					}
//			    }, $("#stageAddUdpForm").getFormData(), false);
//			}
//		});
//	});
//	$("#del2").button();
//	$("#del2").bind("click", function(){
//		if($("#projectid").val().length==0){
//			myDialog("请先保存项目信息！", 0);
//			return;
//		}
//		var selectedRowId = $("#stagegrid").jqGrid("getGridParam", "selarrrow");
//		if (!selectedRowId || selectedRowId.length==0) {
//			myDialog("请选择需要删除的记录!", 0);  
//	        return false;
//		}else{
//			myDialogConfirm("您确定要删除选中的记录嘛？", function(){
//				myAjaxReqJson(path+'/business/delStage.do', function(result, textStatus){
//					myDialog("删除成功！", 1);
//					jQuery("#stagegrid").jqGrid("setGridParam", {
//   	 				 postData : {
//   	 					 projectid : $("#projectid").val()
//   	 				}
//   	 			 });
//   	 			 $("#stagegrid").trigger("reloadGrid");
//			   }, {stageid: selectedRowId}, false);
//			});
//		}
//	});
//	$("#exportExcelStage").button();
//	$("#exportExcelStage").bind("click", function(){
//		if($("#projectid").val().length==0){
//			myDialog("请先保存项目信息！", 0);
//			return;
//		}
//		var url = path+'/business/exportProjectstage.do?projectid='+$("#projectid").val();
//		window.location.href= url;
//	});
	
	// 审批阶段按钮初始化
	// 项目阶段按钮事件
	$("#addExamine").button();
	$("#addExamine").bind("click", function(){
		if($("#projectid").val().length==0){
			myDialog("请先保存项目信息！", 0);
			return;
		}
		addUpdDialog({
			htmlPath: path+'/business/addExmaineStagePage.do', 
			title: "新增审批阶段", 
			args: {projectid: $("#projectid").val()},
			saveFun: function(){
				if(!($("#examinestageAddUdpForm").myValidate())){
					return false;
				}
				myAjaxReqJson(path+'/business/saveExmaineStage.do', function(result, textStatus){
					if(result.result.result=="s"){
						if ($("#exmyFile").val().length > 0) {
							$.ajaxFileUpload({
								url : path
										+ '/business/addExmaineAttach.do?examineid='
										+ result.result.examineid, // submit to
								// UploadFileServlet
								secureuri : false,
								fileElementId : 'exmyFile',
								dataType : 'text', // or json xml whatever you
								success : function(data, status) {
									if (data=="1") {
										myDialog("附件上传失败！", 0);
									}else{
//										$("#exfileDown_li").show();
//										$("#exfileDown_li #exfileDown_name").html(data);
										
										myDialog("保存成功！", 1);
					    	 			$("#consoleId").dialog("close");
					    	 			jQuery("#examinegrid").jqGrid("setGridParam", {
					    	 				postData : {
					    	 					 projectid : $("#projectid").val()
					    	 				}
					    	 			});
					    	 			$("#examinegrid").trigger("reloadGrid");
					    	 			// 更新投资列表，涉及到总投资
					    	 			jQuery("#grid").jqGrid("setGridParam", {
					    	 				postData : {
					    	 					projectid : $("#projectid").val()
					    	 				}
					    	 			});
					    	 			$("#grid").trigger("reloadGrid");
									}
								},
								error : function(data, status, e) {
									myDialog(data, 0);
								}
							});
						}else{
							myDialog("保存成功！", 1);
		    	 			$("#consoleId").dialog("close");
		    	 			jQuery("#examinegrid").jqGrid("setGridParam", {
		    	 				postData : {
		    	 					 projectid : $("#projectid").val()
		    	 				}
		    	 			});
		    	 			$("#examinegrid").trigger("reloadGrid");
		    	 			// 更新投资列表，涉及到总投资
		    	 			jQuery("#grid").jqGrid("setGridParam", {
		    	 				postData : {
		    	 					projectid : $("#projectid").val()
		    	 				}
		    	 			});
		    	 			$("#grid").trigger("reloadGrid");
						}
					}else{
						myDialog("保存失败！", 0);
					}
			    }, $("#examinestageAddUdpForm").getFormData(), false);
			}
		});
	});
	$("#updExamine").button();
	$("#updExamine").bind("click", function(){
		if($("#projectid").val().length==0){
			myDialog("请先保存项目信息！", 0);
			return;
		}
		var selectedRowId = $("#examinegrid").jqGrid("getGridParam", "selarrrow");
		if (!selectedRowId || selectedRowId.length==0) {
			myDialog("请选择需要修改的记录!", 0);  
	        return false;
		}else if(selectedRowId.length > 1){
			myDialog("您选择修改的记录过多!", 0);  
	        return false;
		}
		addUpdDialog({
			htmlPath: path+'/business/addExmaineStagePage.do', 
			title: "修改审批阶段", 
			
			args: {projectid: $("#projectid").val(),examineid: selectedRowId[0]},
			saveFun: function(){
				if(!($("#examinestageAddUdpForm").myValidate())){
					return false;
				}
				myAjaxReqJson(path+'/business/saveExmaineStage.do', function(result, textStatus){
					if(result.result.result=="s"){
						if ($("#exmyFile").val().length > 0) {
							$.ajaxFileUpload({
								url : path
										+ '/business/addExmaineAttach.do?examineid='
										+ result.result.examineid, // submit to
								// UploadFileServlet
								secureuri : false,
								fileElementId : 'exmyFile',
								dataType : 'text', // or json xml whatever you
								success : function(data, status) {
									if (data=="1") {
										myDialog("附件上传失败！", 0);
									}else{
//										$("#exfileDown_li").show();
//										$("#exfileDown_li #exfileDown_name").html(data);
										
										myDialog("保存成功！", 1);
					    	 			$("#consoleId").dialog("close");
					    	 			jQuery("#examinegrid").jqGrid("setGridParam", {
					    	 				postData : {
					    	 					 projectid : $("#projectid").val()
					    	 				}
					    	 			});
					    	 			$("#examinegrid").trigger("reloadGrid");
					    	 			// 更新投资列表，涉及到总投资
					    	 			jQuery("#grid").jqGrid("setGridParam", {
					    	 				postData : {
					    	 					projectid : $("#projectid").val()
					    	 				}
					    	 			});
					    	 			$("#grid").trigger("reloadGrid");
									}
								},
								error : function(data, status, e) {
									myDialog(data, 0);
								}
							});
						}else{
							myDialog("保存成功！", 1);
		    	 			$("#consoleId").dialog("close");
		    	 			jQuery("#examinegrid").jqGrid("setGridParam", {
		    	 				postData : {
		    	 					 projectid : $("#projectid").val()
		    	 				}
		    	 			});
		    	 			$("#examinegrid").trigger("reloadGrid");
		    	 			// 更新投资列表，涉及到总投资
		    	 			jQuery("#grid").jqGrid("setGridParam", {
		    	 				postData : {
		    	 					projectid : $("#projectid").val()
		    	 				}
		    	 			});
		    	 			$("#grid").trigger("reloadGrid");
						}
					}else{
						myDialog("保存失败！", 0);
					}
			    }, $("#examinestageAddUdpForm").getFormData(), false);
			}
		});
	});
	$("#delExamine").button();
	$("#delExamine").bind("click", function(){
		if($("#projectid").val().length==0){
			myDialog("请先保存项目信息！", 0);
			return;
		}
		var selectedRowId = $("#examinegrid").jqGrid("getGridParam", "selarrrow");
		if (!selectedRowId || selectedRowId.length==0) {
			myDialog("请选择需要删除的记录!", 0);  
	        return false;
		}else{
			myDialogConfirm("您确定要删除选中的记录嘛？", function(){
				myAjaxReqJson(path+'/business/delExmaineStage.do', function(result, textStatus){
					myDialog("删除成功！", 1);
					jQuery("#examinegrid").jqGrid("setGridParam", {
	   	 				postData : {
	   	 					 projectid : $("#projectid").val()
	   	 				}
   	 			 	});
   	 			 	$("#examinegrid").trigger("reloadGrid");
	   	 			// 更新投资列表，涉及到总投资
	 	 			jQuery("#grid").jqGrid("setGridParam", {
	 	 				postData : {
	 	 					projectid : $("#projectid").val()
	 	 				}
	 	 			});
	 	 			$("#grid").trigger("reloadGrid");
			   }, {stageid: selectedRowId}, false);
			});
		}
	});
	$("#exportExamineExcel").button();
	$("#exportExamineExcel").bind("click", function(){
		if($("#projectid").val().length==0){
			myDialog("请先保存项目信息！", 0);
			return;
		}
		var url = path+'/business/exportExamine.do?projectid='+$("#projectid").val();
		window.location.href= url;
	});
	
	// 初始化表格
	$.fn.myDataGrid({
		// 列表数据请求路径
		gridUrl: path+'/business/queryInvest.ajax',     
		postData : {projectid : $("#projectid").val()},
		// 表格渲染table ID
		gridId: 'grid',
		// 分页渲染DIV ID
		pagerId: 'page',
		// 高度
		height: 150,
		// 表头
		colNames: ['INVESTMENTID', '文件名称', '文号', '下达时间', '资金来源', '下达资金', '下达资金比例', '累计比例', '备注', '最新附件'],
		colModel: [{								
				name:'INVESTMENTID',
				index:'INVESTMENTID',
				sortable:false,
				hidden:true
			},{
			 	name:'REFERENCENAME',
			 	index:'REFERENCENAME', 
			 	sortable:false
			},{
			 	name:'REFERENCECODE',
			 	index:'REFERENCECODE', 
			 	sortable:false
			},{
		   		name:'INVESTMENTTIME',					
		   		index:'INVESTMENTTIME',	
		   		sortable:false
	   		},{
		   		name:'FUNDING_NAME',					
		   		index:'FUNDING_NAME',	
		   		sortable:false
	   		},{
			 	name:'ACTUALINVEST',
			 	index:'ACTUALINVEST', 
			 	sortable:false
	   		},{
	   			name:'SCALECOUNT',
	   			index:'SCALECOUNT', 
	   			sortable:false
	   		},{
	   			name:'TOTALSCALECOUNT',
	   			index:'TOTALSCALECOUNT', 
	   			sortable:false
		   	},{
	   			name:'REMARKS',
	   			index:'REMARKS', 
	   			sortable:false
		   	}, {
				name : 'operate',
				sortable : false,
				align : 'center',
				formatter : operateIN
		   	}]
	});
	
	jQuery("#grid").jqGrid("setGridParam", {
		ondblClickRow: function(rowid){
			showInfo(rowid);
		}
	});
	
	// 初始化表格
//	$.fn.myDataGrid({
//		// 列表数据请求路径
//		gridUrl: path+'/business/getStageList.ajax',   
//		postData : {projectid : $("#projectid").val()},
//		// 表格渲染table ID
//		gridId: 'stagegrid',
//		// 分页渲染DIV ID
//		pagerId: 'stagepage',
//		// 高度
//		height: 150,
//		// 表头
//		colNames: ['STAGEID', '建设阶段', '负责人', '开始日期', '结束日期', '操作'],
//		colModel: [{								
//			name:'STAGEID',
//			index:'STAGEID',
//			sortable:false,
//			hidden:true
//			},{
//	   		name:'STAGE',					
//	   		index:'STAGE',	
//	   		sortable:true
//	   		},{
//	   		name:'RESPONSIBLEPERSON',					
//	   		index:'RESPONSIBLEPERSON',	
//	   		sortable:true
//	   		},{
//   		 	name:'STARTTIME',
//   		 	index:'STARTTIME', 
//   		 	sortable:true
//	   		},{
//   		 	name:'ENDTIME',
//   		 	index:'ENDTIME', 
//   		 	sortable:true
//	   		},{
//   			name:'operate',
//   			sortable:false,
//   			align:'center',
//   			formatter:operate2
//	   		}
//   		]
//	});
	
	// 初始化表格
	$.fn.myDataGrid({
		// 列表数据请求路径
		gridUrl : path + '/business/getExmaineStageList.ajax',
		postData : {
			projectid : $("#projectid").val()
		},
		// 表格渲染table ID
		gridId : 'examinegrid',
		// 分页渲染DIV ID
		pagerId : 'examinepage',
		// 高度
		height : 150,
		// 表头
		colNames : [ 'EXAMINEID', '审批阶段', '文件名称', '文号', '审批时间', '审批金额', '备注',
				'最新附件' ],
		colModel : [ {
			name : 'EXAMINEID',
			index : 'EXAMINEID',
			sortable : false,
			hidden : true
		}, {
			name : 'STAGE',
			index : 'STAGE',
			sortable : false
		}, {
			name : 'DOCUMENTNAME',
			index : 'DOCUMENTNAME',
			sortable : false
		}, {
			name : 'DNUMBER',
			index : 'DNUMBER',
			sortable : false
		}, {
			name : 'REPLYTIME',
			index : 'REPLYTIME',
			sortable : false
		}, {
			name : 'REPLYMONEY',
			index : 'REPLYMONEY',
			sortable : false
		}, {
			name : 'REMARK',
			index : 'REMARK',
			sortable : false
		}, {
			name : 'operate',
			sortable : false,
			align : 'center',
			formatter : operate3
		}]
	});

	jQuery("#examinegrid").jqGrid("setGridParam", {
		ondblClickRow: function(rowid){
			showInfo3(rowid);
	}});
});

//function projecttypeChange(projecttype, projecttype2){
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
//	if(projecttype2){
//		$("#projecttype2").val(projecttype2);
//	}
//}

//function scaleCount(cellvalue, options, rowObject){
//	var res = 0;
//	if(rowObject.ACTUALINVESTTOTAL > 0&&rowObject.ACTUALINVEST!=""&&rowObject.ACTUALINVEST!=null){
//		res = (rowObject.ACTUALINVEST*1.0/rowObject.ACTUALINVESTTOTAL*100).toFixed(2)+"%";
//	}
//	return res;
//}

//操作
//function operate(cellvalue, options, rowObject) {
//	return "<a href='javascript:;' onclick='showInfo(\"" + rowObject.INVESTMENTID
//			+ "\")' class='oprateCss'>查看详细</a>";
//}

//function operate2(cellvalue, options, rowObject) {
//	return "<a href='javascript:;' onclick='showInfo2(\"" + rowObject.STAGEID
//			+ "\")' class='oprateCss'>查看详细</a>";
//}

// 附件下载
function operateIN(cellvalue, options, rowObject) {
	if(rowObject.FILEPATH!=null){
		return "<a href='javascript:;' onclick='downloadIN(\"" + rowObject.FILEPATH
			+ "\")' class='oprateCss'>打开</a>";
	}else{
		return "没有附件";
	}
}

function downloadIN(FILEPATH) {
	var url = path + '/business/downloadInvestmentByFilePathToOpen.do?filepath='
			+ encodeURI(encodeURI(FILEPATH));
	openNewWindowByURL(url);
}

// 附件下载
function operate3(cellvalue, options, rowObject) {
	if(rowObject.FILEPATH!=null){
		return "<a href='javascript:;' onclick='downloadEx(\"" + rowObject.FILEPATH
			+ "\")' class='oprateCss'>打开</a>";
	}else{
		return "没有附件";
	}
}

function downloadEx(FILEPATH) {
	var url = path + '/business/exdownloadByFilePathToOpen.do?filepath='
			+ encodeURI(encodeURI(FILEPATH));
//	$("#downForm").attr("method", "post");
//	$("#downForm").attr("action", url);
//	$("#downForm").submit();
//	myAjaxReqJson(path+'/comm/getConfigManagerPath.do', function(result, textStatus){
//		openNewWindowByURL(path+"/"+result.path+"/"+FILEPATH);
//	}, {tempPath:"/configuration/uploadfile/examineuploadPath"}, true);
	openNewWindowByURL(url);
}

function showInfo(investmentid) {
	addUpdDialog({
		htmlPath: path+'/business/addIvestmentPage.do', 
		title: "查看投资", 
		args: {projectid: $("#projectid").val(), investmentid: investmentid, SEARCH_FLAG: "SEARCH"}
	});
}

//function showInfo2(stageid) {
//	addUpdDialog({
//		htmlPath: path+'/business/addStagePage.do', 
//		title: "查看项目阶段", 
//		args: {projectid: $("#projectid").val(), stageid: stageid, SEARCH_FLAG: "SEARCH"}
//	});
//}

function showInfo3(examineid) {
	addUpdDialog({
		htmlPath: path+'/business/addExmaineStagePage.do', 
		title: "查看审批阶段", 
		args: {projectid: $("#projectid").val(), examineid: examineid, SEARCH_FLAG: "SEARCH"}
	});
}