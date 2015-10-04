</div>
<%@page import="net.ko.debug.KDebugClient"%>
<%@page import="net.ko.framework.KoHttp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- footer -->
<%String path=request.getServletContext().getContextPath(); 
if((request.getServerName()).contains("kobject"))
	path="";
%>
<div id="main-ajax-loader" style="display: none"><span>Chargement...</span></div>
<script src="<%=path%>/js/userJS.js"></script>
<script src="<%=path%>/js/sizzle.js"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>

<script type="text/javascript" src="messages.kjs"></script>
<%
if(KDebugClient.isActive()){
	out.print(KDebugClient.getMenu(request));
}
%>
<%=KoHttp.kajaxIncludes(request)%>
<script type="text/javascript">
google.load('visualization', '1', {packages:['corechart','table','map']});
</script>
</body>
</html>