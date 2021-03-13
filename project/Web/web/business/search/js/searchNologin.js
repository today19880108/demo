$(function(){
	$("#treePanel").show(); //处理easyui，不设置title无法显示问题
	
	$("[name*=leftTreeNum_]").bind("click", function(){
		$("[name*=leftTreeNum_]").find("img").removeClass("selectBorder");
		$(this).find("img").addClass("selectBorder");
		
		var con = "";
		if($(this).attr("name")!="leftTreeNum_searchByAll"){
			con = "&leftTreeNum="+$(this).attr("name").split("_")[1];
		}
		$("#main_content").html('<iframe src="'+path+'/search/projectManagePage.do?SEARCH_FLAG=SEARCH'+con+'" frameborder="0" style="width:100%;height:100%;"></iframe>');
	});
});