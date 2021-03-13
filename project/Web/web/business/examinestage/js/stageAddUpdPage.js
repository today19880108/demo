$(function() {
	if($("#SEARCH_FLAG3").val()!='SEARCH'){
		jQuery('#examinestageAddUdpForm #replytime').datepicker({
			changeMonth: true, 
		    changeYear: true, 
		    dateFormat: "yy-mm-dd"
		});
	} else{
		setReadOnly("examinestageAddUdpForm");
	}
	var examineid = $("#examineid").val();
	if(examineid.length > 0){
		myAjaxReqJson(path+'/business/getAttchListByExid.do', function(result, textStatus){
			var data = result.list;
			if(data.length > 0){
	    		var content="";
				for(var i=0;i<data.length;i++){
					content+=
						'<li style="width: 100%;padding: 1px;'+(i==0?' margin-top: 5px;':'')+'">'+
						'<div style="width:100px;float:left;text-align:right;">'+(i==0?'附件下载：':'&nbsp;')+'</div>'+
						'<div style="float: left;width: '+($("#SEARCH_FLAG3").val()=="SEARCH"?456:524)+'px;height: 24px;line-height: 24px;">'+data[i].FILEPATH.substring(data[i].FILEPATH.indexOf("_")+1)+'</div>'+
						(
						$("#SEARCH_FLAG3").val()=="SEARCH"?
							('<input type="button" value="下载" onclick="exfileDown(\''+data[i].FILEPATH+'\')" style="width: 62px;height: 24px;line-height: 24px; margin-left: 5px;"/>'
							+
							'<input type="button" value="打开" onclick="downloadEx(\''+data[i].FILEPATH+'\')" style="width: 62px;height: 24px;line-height: 24px; margin-left: 5px;"/>'
							)
						    :
							'<input type="button" value="删除" onclick="exfileDelById(\''+data[i].ATTID+'\')" delFlag="exfileDelId'+data[i].ATTID+'" style="width: 62px;height: 24px;line-height: 24px; margin-left: 5px;"/>'
						)+
						'</li>';
				}
				$("#examinestageAddUdpFormUL").append(content);
	    	}
		}, {examineid:examineid}, false);
	}
});

//下载文件
function exfileDown(filePath){
	var url = path+'/business/exdownloadByFilePath.do?filepath='+encodeURI(encodeURI(filePath));
	$("#examinestageAddUdpForm").attr("method", "post");
	$("#examinestageAddUdpForm").attr("action", url);
	$("#examinestageAddUdpForm").submit();
}

function exfileDelById(ATTID){
	myDialogConfirm("您确定删除该附件？", function(){
		myAjaxReqJson(path+'/business/exfileDelById.do', function(result, textStatus){
			$("[delFlag=exfileDelId"+ATTID+"]").parent().remove();
			jQuery("#examinegrid").jqGrid("setGridParam", {
 				postData : {
 					 projectid : $("#projectid").val()
 				}
 			});
 			$("#examinegrid").trigger("reloadGrid");
		}, {ATTID:ATTID}, false);
	});
}
