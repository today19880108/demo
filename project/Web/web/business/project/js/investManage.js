$(function(){
//	var year = $("#year").val()*1;
//	var optionStr = "";
//	for(var i = year; i>=2000; i--){
//		optionStr+='<option value="'+i+'" '+(i==year?"selected":"")+'>'+i+'</option>';
//	}
//
//	$("#investmenttime").html(optionStr);
//	$("#investmenttime").val($("#investmenttime_hidden").val());

	if($("#SEARCH_FLAG2").val()=='SEARCH'){
		setReadOnly("addInvestForm");
	}else{
		jQuery('#addInvestForm #investmenttime').datepicker({
			changeMonth: true, 
		    changeYear: true, 
		    dateFormat: "yy-mm-dd"
		});
	}
	
	var investmentid = $("#investmentid").val();
	if(investmentid.length > 0){
		myAjaxReqJson(path+'/business/getAttchListByInid.do', function(result, textStatus){
			var data = result.list;
	    	if(data.length > 0){
	    		var content="";
				for(var i=0;i<data.length;i++){
					content+=
						'<li style="width: 100%;padding: 1px;'+(i==0?' margin-top: 5px;':'')+'">'+
						'<div style="width:100px;float:left;text-align:right;">'+(i==0?'附件下载：':'&nbsp;')+'</div>'+
						'<div style="float: left;width: '+($("#SEARCH_FLAG2").val()=="SEARCH"?456:524)+'px;height: 24px;line-height: 24px;">'+data[i].FILEPATH.substring(data[i].FILEPATH.indexOf("_")+1)+'</div>'+
						(
							$("#SEARCH_FLAG2").val()=="SEARCH"?
							('<input type="button" value="下载" onclick="infileDown(\''+data[i].FILEPATH+'\')" style="width: 62px;height: 24px;line-height: 24px; margin-left: 5px;"/>'
							+
							'<input type="button" value="打开" onclick="downloadIN(\''+data[i].FILEPATH+'\')" style="width: 62px;height: 24px;line-height: 24px; margin-left: 5px;"/>'
							)
							:
							'<input type="button" value="删除" onclick="infileDelById(\''+data[i].ATTACHMENTID+'\')" delFlag="infileDelId'+data[i].ATTACHMENTID+'" style="width: 62px;height: 24px;line-height: 24px; margin-left: 5px;"/>'
						)+
						'</li>';
				}
				$("#addInvestFormUL").append(content);
	    	}
		}, {investmentid:investmentid}, false);
	}
});

//下载文件
function infileDown(filePath){
	var url = path+'/business/downloadInvestmentByFilePath.do?filepath='+encodeURI(encodeURI(filePath));
	$("#addInvestForm").attr("method", "post");
	$("#addInvestForm").attr("action", url);
	$("#addInvestForm").submit();
}

function infileDelById(ATTACHMENTID){
	myDialogConfirm("您确定删除该附件？", function(){
		myAjaxReqJson(path+'/business/infileDelById.do', function(result, textStatus){
			$("[delFlag=infileDelId"+ATTACHMENTID+"]").parent().remove();
			jQuery("#grid").jqGrid("setGridParam", {
 				postData : {
 					 projectid : $("#projectid").val()
 				}
 			});
 			$("#grid").trigger("reloadGrid");
		}, {ATTACHMENTID:ATTACHMENTID}, false);
	});
}

