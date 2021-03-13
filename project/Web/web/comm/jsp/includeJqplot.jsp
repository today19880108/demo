<%
String path = request.getContextPath();
%>
<!--[if lt IE 9]><script language="javascript" type="text/javascript" src="<%=path %>/ui/jqplot/excanvas.min.js"></script><![endif]--> 
<script type="text/javascript" src="<%=path %>/ui/jqplot/jquery.jqplot.min.js"></script> 
<link rel="stylesheet" type="text/css" href="<%=path %>/ui/jqplot/jquery.jqplot.min.css" /> 

<script type="text/javascript" src="<%=path %>/ui/jqplot/plugins/jqplot.pieRenderer.min.js"></script>

<script type="text/javascript" src="<%=path %>/ui/jqplot/plugins/jqplot.barRenderer.min.js"></script>
<script type="text/javascript" src="<%=path %>/ui/jqplot/plugins/jqplot.categoryAxisRenderer.min.js"></script>
<script type="text/javascript" src="<%=path %>/ui/jqplot/plugins/jqplot.pointLabels.min.js"></script>

<script type="text/javascript" src="<%=path %>/ui/jqplot/plugins/jqplot.canvasTextRenderer.min.js"></script>
<script type="text/javascript" src="<%=path %>/ui/jqplot/plugins/jqplot.canvasAxisLabelRenderer.min.js"></script>
<script type="text/javascript" src="<%=path %>/ui/jqplot/plugins/jqplot.canvasAxisTickRenderer.min.js"></script>

<script type="text/javascript" src="<%=path %>/ui/jqplot/plugins/jqplot.highlighter.min.js"></script>
<script type="text/javascript" src="<%=path %>/ui/jqplot/plugins/jqplot.enhancedLegendRenderer.min.js"></script>
