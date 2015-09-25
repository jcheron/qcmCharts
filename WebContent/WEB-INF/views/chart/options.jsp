<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="net.ko.utils.KString"%>
<%@page import="org.json.JSONArray"%>
<%@page import="net.ko.framework.KoHttp"%>
<%@page import="net.ko.types.HtmlControlType"%>
<%@page import="net.ko.http.views.KHtmlFieldControl"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Map<String,Object> values=new HashMap<String,Object>();
Enumeration<String> names=request.getParameterNames();
while(names.hasMoreElements()){
	String name=names.nextElement();
	if(!name.startsWith("_") && !name.startsWith("request.id") && !"".equals(request.getParameter(name))){
		String parameter=request.getParameter(name);
		if(parameter.startsWith("[")){
			JSONArray oParameter;
			try{
				oParameter=new JSONArray(parameter);
				values.put(name,oParameter);
			}catch(Exception e){
				values.put(name,parameter);
			}
		}else if(parameter.startsWith("{")){
			JSONObject oObject;
			try{
				oObject=new JSONObject(parameter);
				values.put(name,oObject);
			}catch(Exception e){
				values.put(name,parameter);
			}
		}else{
			if(KString.isBoolean(parameter))
				values.put(name,KString.isBooleanTrue(parameter));
			else if(KString.isNumeric(parameter)){
				Number n=NumberFormat.getNumberInstance(Locale.FRENCH).parse(parameter);
				values.put(name,n);
			}else
				values.put(name, parameter);
		}
	}
}
JSONObject options=new JSONObject(values);
KHtmlFieldControl fc=new KHtmlFieldControl("Options",options+"","options","options",HtmlControlType.khcTextarea);
out.print(fc);
out.print(KoHttp.kajaxIncludes(request));
%>