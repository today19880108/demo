$(function(){
	var year = $("#year").val()*1;
	var optionStr = "";
	for(var i = year; i>=2000; i--){
		optionStr+='<option value="'+i+'">'+i+'</option>';
	}
	
	// 年度总投资分析
	$("#year_s,#year_e").html(optionStr);
	$("#year_s").val(year-5);
	$("#year_e").val(year);
	
	setYearAnalyse();
	$("#year_s,#year_e").bind("change", function(){
		if($("#year_s").val() > $("#year_e").val()){
			myDialog("请正确选择年份范围！", 0);  
			return false;
		}
		$("#lineId").html("");
		setYearAnalyse();
	});
	
	// 项目类型投资分析
	setProjectTypeAnalyse();
	
	// 年度项目类型投资分析
	$("#year_s2,#year_e2").html(optionStr);
	$("#year_s2").val(year-5);
	$("#year_e2").val(year);
	
	setYearProjectTypeAnalyse();
	$("#year_s2,#year_e2").bind("change", function(){
		if($("#year_s2").val() > $("#year_e2").val()){
			myDialog("请正确选择年份范围！", 0);  
			return false;
		}
		$("#lineNumId").html("");
		setYearProjectTypeAnalyse();
	});
});

// 年度总投资分析
function setYearAnalyse(){
	var year_s = $("#year_s").val(); 
	var year_e = $("#year_e").val();
	myAjaxReqJson(path+'/business/getYearAnalyseData.ajax', function(result, textStatus){
		var lineData = [];
		var data = result.list;
		for(var i = year_s; i<=year_e; i++){
			var temp = [];
			var tempFlag = false;
			$.each(data, function(index, v){
				if(i==v.INVESTMENTTIME){
					temp.push(i);
					temp.push(v.ACTUALINVEST);
					tempFlag = true;
					
					data.splice(index, 1);
					return false;
				}
			});
			if(!tempFlag){
				temp.push(i);
				temp.push(0);
			}
			lineData.push(temp);
		}
		var yearLine = jQuery.jqplot('lineId', [lineData], {
			title: '',
		    seriesDefaults: { 
	            pointLabels: { 
	            	show: true,
	            	formatString: "%.2f"
	            }
	        },
		    axesDefaults: {
		    	//IE8显示有问题		    	
		        //labelRenderer: $.jqplot.CanvasAxisLabelRenderer
		    },
		    axes: {
		        xaxis: {
		        	renderer: $.jqplot.CategoryAxisRenderer,
		        	label: "",
	                tickRenderer: $.jqplot.CanvasAxisTickRenderer,
	            	tickOptions: {
	            		fontSize: '12px', 
	    	        	angle: -30
	    	        }
		        },
		        yaxis: {
		        	label: "万元"
		        }
		    },
		    grid: {
				background: '#fff',      
				borderWidth: 0,    
				shadow: false
			},
			highlighter: {
				show: true,
				sizeAdjust: 10,
		        tooltipLocation: 'n',
		        tooltipOffset: 15,
		        tooltipAxes: 'y',
		        useAxesFormatters: false,
		        tooltipFormatString: "%.2f万元"
		    }
	    });
    }, {year_s: year_s, year_e: year_e}, true);
}

// 项目类型投资分析
function setProjectTypeAnalyse(){
	var projectTypeList = eval("("+$("#projectTypeList").val()+")"); 
	myAjaxReqJson(path+'/business/getProjectTypeAnalyseData.ajax', function(result, textStatus){
		var barData = [];
		var data = result.list;
		for(var i = 0; i < projectTypeList.length; i++){
			var temp = [];
			var tempFlag = false;
			$.each(data, function(index, v){
				if(projectTypeList[i].ID==v.PROJECTTYPE){
					temp.push(projectTypeList[i].REMARK);
					temp.push(v.ACTUALINVEST);
					tempFlag = true;
					
					data.splice(index, 1);
					return false;
				}
			});
			if(!tempFlag){
				temp.push(projectTypeList[i].REMARK);
				temp.push(0);
			}
			barData.push(temp);
		}
		
		var bar = jQuery.jqplot('barId', [barData], {
	        title: '',
	        seriesDefaults: { 
	            renderer: $.jqplot.BarRenderer,
	            rendererOptions: {
	            	barWidth: 30,
	            	varyBarColor: true
	            },
	            pointLabels: { 
	            	show: true,
	            	formatString: "%.2f"
	            }
	        },
	        axes: {
	            xaxis: {
	                renderer: $.jqplot.CategoryAxisRenderer,
	                label: '',
	                tickRenderer: $.jqplot.CanvasAxisTickRenderer,
	            	tickOptions: {
	            		fontSize: '12px', 
	    	        	angle: -30
	    	        }
	            },
	            yaxis: { 
	            	label: '万元'  
	            }
	        },
		    grid: {
				background: '#fff',      
				borderWidth: 0,    
				shadow: false
			}
	    });
    }, {}, true);
}

// 年度项目类型投资分析
function setYearProjectTypeAnalyse(){
	var year_s = $("#year_s2").val(); 
	var year_e = $("#year_e2").val();
	
	var projectTypeList = eval("("+$("#projectTypeList").val()+")"); 
	myAjaxReqJson(path+'/business/getYearProjectTypeAnalyseData.ajax', function(result, textStatus){
		var lineDataAll = [];
		var series = [];
		$.each(projectTypeList, function(pindex, p){
			var lineData = [];
			var data = result.list;
			for(var i = year_s; i<=year_e; i++){
				var temp = [];
				var tempFlag = false;
				$.each(data, function(index, v){
					if(i==v.INVESTMENTTIME&&p.ID==v.PROJECTTYPE){
						temp.push(i);
						temp.push(v.ACTUALINVEST);
						tempFlag = true;
						
						data.splice(index, 1);
						return false;
					}
				});
				if(!tempFlag){
					temp.push(i);
					temp.push(0);
				}
				lineData.push(temp);
			}
			series.push({label: p.REMARK});
			lineDataAll.push(lineData);
		});
			
		var lineNum = jQuery.jqplot('lineNumId', lineDataAll, {
			title: '',
		    seriesDefaults: { 
	            pointLabels: { 
	            	show: true,
	            	formatString: "%.2f"
	            }
	        },
	        series: series,
	        legend: { 
	        	show: true,
	        	location: 'e',
	        	placement: "outsideGrid"
		    },
		    axesDefaults: {
		    	//IE8显示有问题
		        //labelRenderer: $.jqplot.CanvasAxisLabelRenderer
		    },
		    axes: {
		        xaxis: {
		        	renderer: $.jqplot.CategoryAxisRenderer,
		        	label: "",
	                tickRenderer: $.jqplot.CanvasAxisTickRenderer,
	            	tickOptions: {
	            		fontSize: '12px', 
	    	        	angle: -30
	    	        }
		        },
		        yaxis: {
		        	label: "万元"
		        }
		    },
		    grid: {
				background: '#fff',      
				borderWidth: 0,    
				shadow: false
			},
			highlighter: {
				show: true,
				sizeAdjust: 10,
		        tooltipLocation: 'n',
		        tooltipOffset: 15,
		        tooltipAxes: 'y',
		        useAxesFormatters: false,
		        tooltipFormatString: "%.2f万元"
		    }
	    });
    }, {year_s: year_s, year_e: year_e}, true);
}

