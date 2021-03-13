// 提示信息 status 0：普通 1：成功 2：失败
var myDialog = function(msg, status){
//	var $temp;     
//    if(window.location!=window.parent.location){  
//    	$temp=window.parent.jQuery;
//    }else{  
    	$temp=jQuery;     
//    } 
	var str = '<div id="dialog-message" style="padding:15px 10px;text-align:center;">'+msg+'</div>';
	var titleClass = "ui-icon-info";
	if(status=="1"){
		titleClass = "ui-icon-circle-check";
	}else if(status=="2"){
		titleClass = "ui-icon-circle-close";
	}
	$temp("body").append(str);
	$temp("#dialog-message").dialog({
		modal: true,
		position: ["center", 120],
		title: '<span class="ui-icon '+titleClass+'" style="float:left;"></span>提示信息',
		width: 230,
		resizable: false,
		buttons:{
			"关闭":function(){
				$temp(this).dialog("close");
			}
		},
		close: function(){
			$(this).remove();
		}
	});
	if(status==1){
		setTimeout(function(){
			$temp("#dialog-message").dialog("close");
		},1000);
	}
};
var myPDialog = function(msg, status){
	var $temp;     
    if(window.location!=window.parent.location){  
    	$temp=window.parent.jQuery;
    }else{  
    	$temp=jQuery;     
    } 
	var str = '<div id="dialog-message" style="padding:15px 10px;text-align:center;">'+msg+'</div>';
	var titleClass = "ui-icon-info";
	if(status=="1"){
		titleClass = "ui-icon-circle-check";
	}else if(status=="2"){
		titleClass = "ui-icon-circle-close";
	}
	$temp("body").append(str);
	$temp("#dialog-message").dialog({
		modal: true,
		position: ["center", 120],
		title: '<span class="ui-icon '+titleClass+'" style="float:left;"></span>提示信息',
		width: 230,
		resizable: false,
		open: function(){
			$temp(this).parent().find("button").css("margin", "0 0.4em 0 0");
			$temp(this).css("min-height", "0");
		},
		buttons:{
			"关闭":function(){
				$temp(this).dialog("close");
			}
		},
		close: function(){
			$(this).remove();
		}
	});
	if(status==1){
		setTimeout(function(){
			$temp("#dialog-message").dialog("close");
		},1000);
	}
};
// 确认信息 status 0：普通 1：成功 2：失败
var myDialogConfirm = function(msg, callback){
//	var $temp;     
//    if(window.location!=window.parent.location){  
//    	$temp=window.parent.jQuery;
//    }else{  
    	$temp=jQuery;     
//    } 
	var str = '<div id="dialog-confirm" style="padding:20px 10px;text-align:center;">'+msg+'</div>';
	$temp("body").append(str);
	$temp( "#dialog-confirm" ).dialog({
		modal: true,
		position: ["center", 120],
		title: '<span class="ui-icon ui-icon-alert" style="float:left;"></span>确认信息',
		width: 230,
		resizable: false,
		close: function(){
			$temp(this).remove();
		},
		buttons:{
			"确定":function(){
				$temp(this).dialog("close");
				callback();
			},
			"取消":function(){
				$temp(this).dialog("close");
			}
		}
	});
};
var myPDialogConfirm = function(msg, callback){
	var $temp;     
    if(window.location!=window.parent.location){  
    	$temp=window.parent.jQuery;
    }else{  
    	$temp=jQuery;     
    } 
	var str = '<div id="dialog-confirm" style="padding:20px 10px;text-align:center;">'+msg+'</div>';
	$temp("body").append(str);
	$temp( "#dialog-confirm" ).dialog({
		modal: true,
		position: ["center", 120],
		title: '<span class="ui-icon ui-icon-alert" style="float:left;"></span>确认信息',
		width: 230,
		resizable: false,
		open: function(){
			$temp(this).parent().find("button").css("margin", "0 0.4em 0 0");
			$temp(this).css("min-height", "0");
		},
		close: function(){
			$temp(this).remove();
		},
		buttons:{
			"确定":function(){
				$temp(this).dialog("close");
				callback();
			},
			"取消":function(){
				$temp(this).dialog("close");
			}
		}
	});
};

// htmlPath：新增页面请求路径
// args：参数
// title：标题
// saveFun：新增/修改保存方法，参数为空时说明没有保存按钮
// diaWeight: 弹出框宽度
// diaHeight: 弹出框高度
var addUpdDialog = function(setting){
	//设置参数默认值
	var params = $.extend({
		consoleId: "consoleId",
		args: {},
		diaWeight: 800,
		position: ["center", 10]
	}, setting);
	
	$("body").append("<div id='"+params.consoleId+"' title='"+params.title+"'><div>");
	
	var buttons;
	if(params.saveFun){
		buttons = {
				"保存": function(){
					params.saveFun();
				},
				"关闭": function(){
					$(this).dialog("close");
				}
		};
	}else{
		buttons = {
				"关闭": function(){
					$(this).dialog("close");
				}
		};
	}
	$("#"+params.consoleId).dialog({  
		autoOpen: true,  
		modal: true, 
		resizable: false,  
		width: params.diaWeight,
		height: params.diaHeight,
		position: params.position,
		buttons: buttons,
		overlay: {
	        opacity: 0.6
	    },
		open: function(){
			myAjaxReqHtml(params.htmlPath, 
				function(data){
				    $("#"+params.consoleId).html(data);
				}, 
			params.args, false);
		},
		close: function(){
			$(this).remove();
		}
	}); 
};


