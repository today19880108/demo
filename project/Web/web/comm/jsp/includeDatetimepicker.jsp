<!-- datepicker在jquery.ui中，这里加入timepicker（jquery并没有实现，开源人士提供） -->
<%
String path = request.getContextPath();
%>
<script type="text/javascript" src="<%=path %>/ui/datetimepicker/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="<%=path %>/ui/datetimepicker/jquery-ui-timepicker-zh-CN.js"></script> 
<link rel="stylesheet" type="text/css" href="<%=path %>/ui/datetimepicker/jquery-ui-timepicker-addon.css"></link>
