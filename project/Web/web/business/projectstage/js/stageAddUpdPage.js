$(function(){
	if($("#SEARCH_FLAG3").val()!='SEARCH'){
		jQuery('#stageAddUdpForm #starttime2').datepicker({
			changeMonth: true, 
		    changeYear: true, 
		    dateFormat: "yy-mm-dd"
		});
		jQuery('#stageAddUdpForm #endtime2').datepicker({
			changeMonth: true, 
		    changeYear: true, 
		    dateFormat: "yy-mm-dd"
		});
	}else{
		setReadOnly("stageAddUdpForm");
	}
});