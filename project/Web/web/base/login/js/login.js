$(function(){
	$("#login").button({
		icons: {primary: "ui-icon-key"}
	});
	$("#reset").button({
		icons: {primary: "ui-icon-refresh"}
	});
	$("#login").bind("click", function(){
		if(beforeSubmit()){
			toSubmit();
		}
	});
	$("#reset").bind("click", function(){
		$("[name=USER_LOGIN],[name=USER_PWD]").removeAttr("class");
		$("[name=USER_LOGIN],[name=USER_PWD]").val("");
		$("#errorInfo").html("");
	});
	$("[name=USER_PWD]").keyup(function(e){
		if (e.keyCode==13&&beforeSubmit()){
			toSubmit();
		}
	});
	$("[name=USER_LOGIN],[name=USER_PWD]").bind("focus", function(){
		if("error"==$(this).attr("class")){
			$("#errorInfo").html("");
		}
		$(this).attr("class", "focus");
	}).bind("blur", function(){
		$(this).removeAttr("class");
	});
});

var toSubmit = function(){
	$("[name=USER_PWD]").val(Base64.encode($("[name=USER_PWD]").val()));
	$("form#user_form").attr("action", path+"/welcome/doLogin.do")
	.submit();
};

var beforeSubmit = function(){
	var istrue = true;
	$("[name=USER_LOGIN],[name=USER_PWD]").each(function(){
		if($.trim($(this).val()).length==0){
			if("USER_LOGIN"==$(this).attr("name")){
				$("[name=USER_PWD]").removeAttr("class");
				$("#errorInfo").html("请输入用户名");
			}else if("USER_PWD"==$(this).attr("name")){
				$("[name=USER_LOGIN]").removeAttr("class");
				$("#errorInfo").html("请输入密码");
			}
			$(this).attr("class", "error");
			istrue = false;
			return false;
		}
	});
	return istrue;
};