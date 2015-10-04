<%@page import="org.json.JSONObject"%>
<%@page import="net.ko.framework.KoHttp"%>
<%@page import="net.ko.types.HtmlControlType"%>
<%@page import="net.ko.http.views.KHtmlFieldControl"%>
<%@page import="net.ko.kobject.KListObject"%>
<%@page import="net.ko.ksql.KParameterizedInstruction"%>
<%@page import="net.models.KChartOption"%>
<%@page import="net.ko.framework.Ko"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form id="frmChartOptions" name="frmChartOptions">
	<fieldset>
	<%
	String type=request.getParameter("type");
	String options=request.getParameter("options");
	JSONObject jsonObject=new JSONObject(options);
	out.print("<legend>"+type+"  options</legend>");
	KParameterizedInstruction condition=new KParameterizedInstruction("`", "chartoption", "chartType like '%"+type+"%' OR `chartType`='' OR isNull(`chartType`)");
	KListObject<KChartOption> chartOptions=Ko.getDao(KChartOption.class).readAll(condition);
	for(KChartOption chartOption:chartOptions){
		String chartType=chartOption.getType();
		KHtmlFieldControl fc=new KHtmlFieldControl(chartOption.getName(),"",chartOption.getName(),chartOption.getName(),HtmlControlType.khcText);
		try{
			Object o=jsonObject.get(chartOption.getName());
			fc.setValue(o+"");
		}catch(Exception e){
			String defaultValue=chartOption.getDefaultValue();
			if(defaultValue==null)
				defaultValue="";
			fc.setValue(defaultValue);
		}
		if("boolean".equals(chartType)){
			fc.setFieldType(HtmlControlType.khcCheckBox);
		}
		if("float".equals(chartType)){
			fc.setFieldType(HtmlControlType.khcNumber);
			fc.setOptions("step='0.1' max='1' min='0'");
		}
		if("int".equals(chartType)){
			fc.setFieldType(HtmlControlType.khcNumber);
		}
		if("object".equals(chartType)){
			fc.setFieldType(HtmlControlType.khcTextarea);
		}
		if("list".equals(chartType)){
			fc.setFieldType(HtmlControlType.khcCmb);
			fc.setListObject(chartOption.getValues());
		}
		out.print(fc);
	}
	%>
	<div class="boxButtons"><a class="btn" id="btValidateOptions"><span class='glyphicon glyphicon-ok-sign' aria-hidden='true'></span> Appliquer</a></div>
	<%
	out.print(KoHttp.kajaxIncludes(request));
	%>
	</fieldset>
</form>