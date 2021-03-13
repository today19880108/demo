$(function(){
	myAjaxReqJson(path+'/business/getZJAnalyseDataById.ajax', function(result, textStatus){
		var pieData = [];
		var data = result.list;
		$.each(data, function(index, v){
			var temp = [];
			temp.push(v.FUNDING_NAME);
			temp.push(v.ACTUALINVEST);
			pieData.push(temp);
		});
		
		// 饼图没有数据JS会报错
		if(pieData.length==0){
			return;
		}
		var pie = jQuery.jqplot ('dia_pieId', [pieData], {
			title: '', 
		    seriesDefaults: {
		    	shadow: true, 
		    	renderer: jQuery.jqplot.PieRenderer, 
		    	rendererOptions: { 
			        fill: false, 
			        sliceMargin: 4, 
			        showDataLabels: true
		        } 
		    }, 
		    legend: { 
		    	show: true ,
		    	location: 'ne'
		    },
		    grid: {
				background: '#fff',      
				borderWidth: 0,    
				shadow: false
			},
			highlighter: {
				show: true,
		        tooltipLocation: 'n',
		        tooltipOffset: 5,
		        tooltipAxes: 'y',
		        useAxesFormatters: false,
		        tooltipFormatString: "%.2f万元"
		    }
		});    
    }, {PROJECTID: $("#PROJECTID").val()}, true);
	
	myAjaxReqJson(path+'/business/getNFXAnalyseDataById.ajax', function(result, textStatus){
//		var line1Data = [];
		var line2Data = [];
		var data = result.list;
		$.each(data, function(index, v){
//			var temp1 = [];
			var temp2 = [];
//			temp1.push(v.INVESTMENTTIME);
//			temp1.push(v.PLANINVEST);
			temp2.push(v.INVESTMENTTIME);
			temp2.push(v.ACTUALINVEST);
//			line1Data.push(temp1);
			line2Data.push(temp2);
		});
		// 柱状图没有数据JS会报错
		if(line2Data.length==0){
			return;
		}
		var barWidth = null;
		if(line2Data.length < 5){
			barWidth = 40;
		}
		var bar1 = jQuery.jqplot('dia_barId', [line2Data], {
	        title: '',
	        seriesDefaults: { 
	            renderer: $.jqplot.BarRenderer,
	            rendererOptions: {
	            	barWidth: barWidth
	            },
	            pointLabels: { 
	            	show: true,
	            	formatString: "%.2f"
	            }
	        },
	        series:[
//	            {
//	            	label: '计划资金&nbsp;&nbsp;'
//	            }, 
	            {
	            	label: '下达资金&nbsp;'
	            }
	        ],
	        legend: { 
	        	show: true,
	        	location: 's',
	        	placement: "outsideGrid",
	        	renderer: jQuery.jqplot.EnhancedLegendRenderer, 
	        	rendererOptions: {
	                numberRows: 1
	            },
	            marginBottom: '15px'
		    },
	        axes: {
	            xaxis: {
	                renderer: $.jqplot.CategoryAxisRenderer,
	                label: '年份',
	                //设置横坐标倾斜
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
    }, {PROJECTID: $("#PROJECTID").val()}, true);
});