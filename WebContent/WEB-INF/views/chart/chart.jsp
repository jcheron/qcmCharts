<%@page import="net.models.KRapport"%>
<%@page import="net.ko.types.HtmlControlType"%>
<%@page import="net.ko.http.views.KHtmlFieldControl"%>
<%@page import="net.ko.kobject.KListObject"%>
<%@page import="net.ko.mapping.KAjaxInclude"%>
<%@page import="net.ko.ksql.KParameterizedInstruction"%>
<%@page import="net.models.KChart"%>
<%@page import="net.ko.http.objects.KRequest"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="net.ko.db.KDbResultSet"%>
<%@page import="net.ko.framework.Ko"%>
<%@page import="net.ko.db.KDataBase"%>
<%@page import="net.ko.framework.KoHttp"%>
<%@page import="net.chart.Chart"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

String viewName="";
String type="";
String options="";
String description="";
int all=-1;
boolean cont=false;
String divId=KRequest.GETPOST("divId", request, "ca1");
if(KRequest.isPost(request)){
	viewName=request.getParameter("viewName");
	type=request.getParameter("type");
	options=request.getParameter("options");
}else{
	if(request.getParameter("id")!=null){
		KChart kchart=Ko.getDao(KChart.class).readById(request.getParameter("id"));
		if(kchart.isLoaded()){
			viewName=kchart.getViewName();
			options=kchart.getOptions();
			type=kchart.getType();
			description=kchart.getDescription();
		}
	}else if(request.getParameter("all")!=null){
		KChart kchart=new KChart();
		KParameterizedInstruction sql=new KParameterizedInstruction("`","");
		sql.setOrderBy("ordre");
		all=Integer.valueOf(request.getParameter("all"));
		Ko.getDao(KChart.class).read(kchart, sql, all);
		if(kchart.isLoaded()){
			viewName=kchart.getViewName();
			options=kchart.getOptions();
			type=kchart.getType();
			description=kchart.getDescription();
			divId="chart-"+all;
			if(all==0){
				String idRapport=KRequest.GETPOST("idRapport", request,"");
				KListObject<KRapport> rapports=Ko.getDao(KRapport.class).readAll();
				KHtmlFieldControl ctrlRapport=new KHtmlFieldControl("", idRapport, "cmbRapport", "cmbRapport", HtmlControlType.khcCmb);
				ctrlRapport.setPlaceHolder("Sélectionnez le rapport...");
				ctrlRapport.setListObject(rapports);
				out.print("<div class='boxButtons'>"+ctrlRapport+"<a class='btn all' id='btCloseAll' title='Fermer le rapport'><span class='glyphicon glyphicon-off' aria-hidden='true'></span> Fermer le rapport</a>");
				out.print("<a class='btn all' id='btPrintAll' title='Imprimer le rapport'><span class='glyphicon glyphicon-print' aria-hidden='true'></span> Imprimer</a></div>");
				out.print("<input type='hidden' id='chartValue' name='chartValue' value='0'>");
			}
			cont=true;
		}
	}
}
if(!"".equals(viewName) && viewName!=null){
	Chart camembert=new Chart(divId);
	camembert.setType(type);
	KDataBase db=Ko.kdatabase();
	KDbResultSet rs= db.sendQuery("select * from `"+viewName+"`");
	ResultSetMetaData rsmdt = rs.getResultSet().getMetaData();
	int count=rsmdt.getColumnCount();
	List<Object> datas=new ArrayList<Object>(count);
	for(int i=1;i<=count;i++){
		datas.add(rsmdt.getColumnName(i));
	}
	camembert.addData(datas.toArray());
	while(rs.next()){
		datas.clear();
		for(int i=1;i<=count;i++){
			Object o=rs.getObject(i);
			if(Chart.isNumeric(o+""))
				datas.add(Double.valueOf(o+""));
			else
				datas.add(rs.getObject(i));
		}
		camembert.addData(datas.toArray());
	};
	rs.close();
	camembert.setStrOptions(options);
	out.print("<script>"+camembert+"</script>");
	%>
	<div id="<%=divId%>" class="chartContainer"></div>
	<%
	if(!"".equals(description) && description!=null){
		out.print("<div class='descriptionChart'>"+description+"</div>");
	}
}
if(all>-1 && cont){
	out.print("<div id='all-"+(all+1)+"'></div>");
}
out.print(KoHttp.kajaxIncludes(request));
%>