//ajax请求JSON
var myAjaxReqJson = function(url, backFuc, paramData, async){
	$.ajax({
		url: url,
		cache: false,
		data: paramData,
		type:'post',
		dataType: "json",
		async: !!async,
		error: function(XMLHttpRequest, textStatus, errorThrown) {  
			if("timeout"==textStatus){
				myDialog("登录超时，请重新登录！", 0);  
			}else{
				myDialog("系统请求错误: " + textStatus, 0);  
			}
        }, 
        success: backFuc
	});
};

//ajax请求HTML
var myAjaxReqHtml = function(url, backFuc, paramData, async){
	$.ajax({
		url: url,
		cache: false,
		data: paramData,
		type:'post',
		dataType: "html",
		async: !!async,
		error: function(textStatus, errorThrown) {  
			if("timeout"==textStatus){
				myDialog("登录超时，请重新登录！", 0);  
			}else{
				myDialog("系统请求错误: " + textStatus, 0);  
			}
        }, 
        success: backFuc
	});
};

//截字符
function handleStr(str, count){
	return str.substr(0, count);
}

//截字符，超过加...
function cutStr(str ,count){
	if(!str||!count||count==0){
		return "";
	}
	if(str.length > count){
		return str.substr(count)+"...";
	}
	return str;
}

//处理空值
function nvl(str, val){
	if(!val&&val!=0){
		val = "";
	}
	if(!str&&str!=0){
		str = val;
	}
	return str;
}

//重写console.debug为$.debug。IE下不会报错
;(function($){
	$.extend({
		debug : function(obj){
			if(typeof(console)!="undefined" && typeof(console.debug)!="undefined" && typeof(console.debug)=="function"){
				return console.debug(obj);
			}
		}
	});
}(jQuery));

//$("").callFunc(function)(args);
;(function($){
	$.fn.extend({
		callFunc0 : function (funcObj){
			var _this = this;
			return function(){
				if(typeof(funcObj)=="function"){
					funcObj.apply(_this,arguments);
				}
			};
		}
	});
}(jQuery));

//$("").callFunc(function);
;(function($){
	$.fn.extend({
		callFunc1 : function (funcObj){
			var _this = this;
			if(typeof(funcObj)=="function"){
				funcObj.apply(_this,arguments);
			}
		}
	});
}(jQuery));

//序列化jquery对象,fun为序列化后的序列值调增函数
;(function($){
	$.fn.extend({
		getFormData : function(check, fun){
			var serializeData = $(this).find(check?check:":input").serializeArray();
			if(fun){
				fun(serializeData);
			}
			var params = "{";
			jQuery.each(serializeData, function(i, field){
		    	  if(field.value == ""){
		    		  params += "'"+field.name+"'"+":'',";
		    	  }else  {
		    		  params += "'"+field.name+"'"+":'"+field.value + "',";
		    	  }
		      });
			params = params.substring(0, params.length-1);
			params += "}";
			params = params.replace(/\r\n/g , "");
			var json = (new Function("return " + params))();
			return json;
		}
	});
}(jQuery));

//设置可读
function setReadOnly(dataForm) {  
    var items = $("#"+dataForm+" input:not([type=button]),#"+dataForm+" textarea");  
    items.attr("readonly", "readonly");
    
    //待处理
    items = $("#"+dataForm+" input[type=checkbox]");  
    
    items = $("#"+dataForm+" select");  
    items.css("background-color", "#F5F5F5");
    items.each(function(){
//	    $(this).unbind("focus").bind("focus", function(){
//	    	$(this).blur();
//	    });
    	if($(this).val().length==0){
    		$(this).find("option:selected").html("");
//    		$(this).attr("disabled","disabled");
    	}
	    //处理IE
	    var selectVal=$(this).val();
	    $(this).unbind("click").bind("click", function(){
	        $(this).val(selectVal);
	        return false;
	    });
    });
    
    items = $("#"+dataForm+" input[type=radio]"); 
    items.each(function(){
    	var selectVal=$("[name="+$(this).attr("name")+"]:checked").val();
	    $(this).unbind("click").bind("click", function(){
	    	$("[name="+$(this).attr("name")+"][value="+selectVal+"]").attr("checked", "checked");
	    	return false;
	    });
    });
}  

// 初始化树形选择框 -无复选框
// zNodes：节点JSON，
// clickId：触发点对象ID，
// event：触发事件，
// showObjId：显示值对象ID，
// valObjId：隐藏值对象ID
// zindex: 浏览器显示层面  默认1层，
// howShow：展开方式，默认全展开，
// menuWidth：菜单显示板宽度，默认输入框宽度，值为noLimit则根据菜单选项宽度而定
var initSelectZtree = function(setting){
	//设置参数默认值
	var params = $.extend({
		howShow: "all",
		zindex: 1
	}, setting);
	
	var timeStr = new Date().getTime();
	var menuContent = "menuContent_"+timeStr;
	var treeSelectId = "treeSelectId_"+timeStr;
	
	var setting = {
			view: {
				dblClickExpand: true
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
					title: "NAME"
				}
			},
			callback: {
				onClick: onClick
			}
		};
	$("body").append('<div id="'+menuContent+'" class="menuContent" style="display:none; position: absolute; z-index: '+params.zindex+'">'+
						'<ul id="'+treeSelectId+'" class="ztree" style="margin-top:0;'+(params.menuWidth=="noLimit"?"":(' width:'+($("#"+params.showObjId).width()-4)))+'px;"></ul>'+
					'</div>');
	var tree = $.fn.zTree.init($("#"+treeSelectId), setting, params.zNodes);
	if(params.howShow=="all"){
		// 展开树菜单所有节点
		tree.expandAll(true, true, true, false);
	}else if(params.howShow=="firts"){
		// 展开树菜单第一个节点及其子节点
		tree.expandNode(tree.getNodes()[0], true, true, true, false);
	}
	// 给触发点添加指定事件
	$('#'+params.clickId).bind(params.event, showMenu);
	
	function onClick(e, treeId, treeNode) {
		$('#'+params.showObjId).val(treeNode.NAME);
		$('#'+params.valObjId).val(treeNode.ID);
	}
	function showMenu() {
		var show = $('#'+params.showObjId);
		var showOffset = $('#'+params.showObjId).offset();
		$("#"+menuContent).css({left:showOffset.left + "px", top:(showOffset.top+1) + show.outerHeight() + "px"}).slideDown("fast");

		$("body").bind("mousedown", onBodyDown);
	}
	function hideMenu() {
		$("#"+menuContent).fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		if (!(event.target.id == params.clickId || event.target.id == menuContent || $(event.target).parents("#"+menuContent).length>0)) {
			hideMenu();
		}
	}
	
	return treeSelectId;
};

// 新窗口打开页面
function openNewWindowByURL(url){
	var winObj = window;
	while(winObj!=winObj.parent){
		winObj = winObj.parent;
	}
	winObj.open(url);
}