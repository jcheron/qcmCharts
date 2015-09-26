</div>
<%@page import="net.ko.debug.KDebugClient"%>
<%@page import="net.ko.framework.KoHttp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- footer -->
<div id="main-ajax-loader" style="display: none"><span>Chargement...</span></div>
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