$(function(){
	$("#save").button({
		icons: {primary: "ui-icon-disk"}
	});
	
	$("#cancel").button({
		icons: {primary: "ui-icon-cancel"}
	});
	
	$("#avatar").bind("error", function(){
		$(this).attr("src",  path+"/web/business/demo1/image/pix-sample.png");
	});
});