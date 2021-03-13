(function($){
	$.extend($.fn,{
		myDataGrid: function(setting){
			//设置参数
			var params = $.extend({
				width: 1024,
				height: 300,
				rowNum: 10,
				rowList: [10,20,30],
				postData: {},
				validate: false,
				forceFit: true,
				autowidth: true, //默认值为false，如果设为true，则Grid的宽度会根据父容器的宽度自动重算
				datatype: "json",
				multiselect: true
			}, setting);
			// 获取grid对象
			var $grid;
			if(params.gridId){
				$grid = $("#"+params.gridId);
			}else{
				$grid = $(this);
			}
			// 初始化jqGrid
			var tmpGrid = $grid.jqGrid({
				url: params.gridUrl,
				cache: false,
				datatype: params.datatype,
				mtype: 'post',
				prmNames: {
					page: "page",
					rows: "rows",
					sort: "sidx",
					order: "sord"
				},
				colNames: params.colNames,
				colModel: params.colModel,
				rowNum: params.rowNum,
				rowList: params.rowList,
				postData: params.postData,
				pager: "#"+params.pagerId,
				viewrecords: true,
				width: params.width, 
				height: params.height, 
				jsonReader: {  							
					root: "page.records",   			   
				    page: "page.currentPage",   		   
				    total: "page.totalPage", 			   
				    records: "page.totalRecords", 		  
					repeatitems: false, 				
					id: "0"
				},
				multiselect: params.multiselect,
				altRows: true,
				forceFit: params.forceFit,
				autowidth: params.autowidth
			});
			// 添加工具栏
			tmpGrid.jqGrid('navGrid',"#"+params.pagerId,
					{edit:false,add:false,del:false,search:false,refresh:false});
			
			// 在新窗口打开页面按钮
			if(params.newOpenPage){
				$.each(params.newOpenPage, function(i, val){
					tmpGrid.navButtonAdd('#'+params.pagerId, {   
				   		caption: val.caption,   
						buttonicon: val.buttonicon,   
						onClickButton: function(){
							if(val.fun&&!val.fun()){
								return false;
							}
							window.open(val.url+"?"+val.paramStr);
						},  
						position: "first",   
						title: val.title,   
						cursor: "pointer"  
					 });
				});
			}
			// 加入添加按钮
			if(params.insertPage){
				tmpGrid.navButtonAdd('#'+params.pagerId, {   
			   		caption: "新增",   
					buttonicon: "ui-icon-plus",   
					onClickButton: insertDialog,  
					position: "first",   
					title: "新增数据",   
					cursor: "pointer"  
				 });
			}
			// 加入修改按钮
			if(params.updatePage){
				tmpGrid.navButtonAdd('#'+params.pagerId, {   
			    	caption: "修改",   
			       	buttonicon: "ui-icon-pencil",   
			       	onClickButton: updateDialog,  
			       	position: "first",   
			       	title: "修改数据",   
			       	cursor: "pointer"  
			   	});
			}
			// 加入删除按钮
			if(params.deleteUrl){
				tmpGrid.navButtonAdd("#"+params.pagerId, {   
					caption: "删除",   
		        	buttonicon: "ui-icon-trash",   
		            onClickButton: deleteDialog,  
		            position: "first",   
		            title: "删除数据",   
		            cursor: "pointer"  
		         });
			}
			// 初始化弹出框
			var $temp;
			function initDialog(title, args){
//			    if(window.location!=window.parent.location){  
//			    	$temp=window.parent.jQuery;
//			    }else{  
			    	$temp=jQuery;     
//			    } 
			    $temp("body").append("<div id='consoleId' title='"+title+"'><div>");
			    $temp("#consoleId").dialog({  
					autoOpen: true,  
					modal: true, 
					resizable: false,  
					width: params.dlgWidth,  
					height: params.dlgHeight,
					position: ["center", 10],
					open: function(){
						var buttons = {};
						if(title=="新增数据"){
							if(params.resetData){
								buttons = {"保存":insertData,"重置":params.resetData,"关闭":closeDialog};
							}else{
								buttons = {"保存":insertData,"关闭":closeDialog};
							}
						}else{
							if(params.resetData){
								buttons = {"保存":updateData,"重置":params.resetData,"关闭":closeDialog};
							}else{
								buttons = {"保存":updateData,"关闭":closeDialog};
							}
						}
						$temp(this).dialog("option","buttons",buttons);
						var $obj = $temp(this);
						myAjaxReqHtml(params.insertPage,function(data){
							//先隐藏，加载好在整体显示，效果得到改观
							$obj.find("div").hide();
							$obj.find("div").html(data);
							setTimeout(function(){
								$obj.find("div").show();
							},50);
						},args,false);
					},
					close:function(){
						$temp(this).remove();
					}
				}); 
			}
			// 新增对话框
			function insertDialog(){
				//beforeHandle
				if(params.insertPageFun&&!params.insertPageFun({url:params.insertPage})){
					return false;
				}
				var args = {};
				if(params.insertPageParam){
					args = params.insertPageParam();
				}
				//beforeHandle
				initDialog("新增数据", args);
			}
			// 修改对话框
			function updateDialog(){
				//beforeHandle
				var selectedRowId = $grid.jqGrid("getGridParam", "selarrrow");
				if (!selectedRowId || selectedRowId.length==0) {
					myDialog("请选择需要修改的记录!", 0);  
			        return false;
				}else if(selectedRowId.length > 1){
					myDialog("您选择修改的记录过多!", 0);  
			        return false;
				}
				if(params.updatePageFun&&!params.updatePageFun({id:selectedRowId,url:params.updatePage})){
					return false;
				}
				var args = {};
				if(params.updatePageParam){
					args = params.updatePageParam();
				}
				args = $.extend({id:selectedRowId}, args);
				//beforeHandle
				initDialog("修改数据", args);
			}
			// 删除对话框
			function deleteDialog(){
				var selectedRowId = tmpGrid.jqGrid("getGridParam", "selarrrow");
			    if (!selectedRowId || selectedRowId.length==0) {  
			    	myDialog("请先选择需要删除的记录!", 0);  
			        return false;  
			    }
		    	myDialogConfirm("确认要删除勾选的记录？",function(){
		    		var args = {id: selectedRowId};
		    		deleteDate(args);
		    	});
			}
			// 关闭
			function closeDialog(){
				$temp("#consoleId").dialog("close"); 
			}
			// 新增数据
			function insertData(){
				if(params.validate&&!($temp("#consoleId").myValidate(params.validateSelect))){
					return false;
				}
				ajaxPost(params.insertUrl, $temp("#consoleId").getFormData(), '添加成功!');
			}
			// 修改数据
			function updateData(){
				if(params.validate&&!($temp("#consoleId").myValidate(params.validateSelect))){
					return false;
				}
				ajaxPost(params.updateUrl, $temp("#consoleId").getFormData(), '修改成功!');
			}
			// 删除数据
			function deleteDate(data){
				ajaxPost(params.deleteUrl, data, '删除成功!');
			}
			// 请求处理 
			function ajaxPost(url, data, msg){
				myAjaxReqJson(url, 
					function(data, textStatus){
						if(data.res){
		            		myDialog(msg, 1);
		            		$temp("#consoleId").dialog("close");
		            		// 重新加载表格
							tmpGrid.jqGrid("setGridParam", {
								datatype: "json" 
							});
		            		tmpGrid.trigger("reloadGrid"); 
						}else if(data.msg){
							myDialog(data.msg, 0);
						}
		            }, data, false);
			}
		}
	});
})(jQuery);