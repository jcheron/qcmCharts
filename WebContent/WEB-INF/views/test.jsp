<%@page import="net.ko.framework.KoHttp"%>
<%@page import="net.ko.http.js.KJavaScript"%>
<%@page import="net.ko.dao.DatabaseDAOObjectUtils"%>
<%@page import="net.display.Questions"%>
<%@page import="net.ko.kobject.KListObject"%>
<%@page import="net.ko.http.views.KHtmlFieldControl"%>
<%@page import="net.ko.types.HtmlControlType"%>
<%@page import="net.models.KQuestion"%>
<%@page import="net.ko.framework.Ko"%>
<%@page import="net.models.KReponse"%>
<%@page import="java.util.Enumeration"%>
<%@page import="net.ko.http.objects.KRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String sReponse="";
boolean good=false;
int idQuestion=-1;
String libelle="";

Enumeration<String> names= request.getParameterNames();
while(names.hasMoreElements()&&!good){
	String name=names.nextElement();
	if(name.startsWith("value-")){
		idQuestion=Integer.valueOf(name.replace("value-", ""));
		libelle=request.getParameter(name);
		good=true;
	}
}
if(good){
	String values="";
	if(request.getParameter("reponses"+idQuestion+"Value")!=null){
		values=request.getParameter("reponses"+idQuestion+"Value");
	}
	KQuestion q = Ko.getDao(KQuestion.class).readById(idQuestion);
	KListObject<KReponse> reponses = q.getReponses();

	KReponse reponse=new KReponse();
	boolean reponseExists=DatabaseDAOObjectUtils.exists(reponse, Ko.kdatabase(), "(libelle like '%"+libelle+"%' OR INSTR('"+libelle+"', libelle) > 0) AND idQuestion="+idQuestion);
	
	
	if(!reponseExists || libelle.startsWith("_")){
		reponse=new KReponse();
		libelle=libelle.replace("_", "");
		reponse.setIdQuestion(idQuestion);
		reponse.setLibelle(libelle);
		reponse.setOrdre(reponses.count());
		if(Ko.getDao(KReponse.class).create(reponse)!=null){
			q.getReponses().add(reponse);
		}
	}
	KHtmlFieldControl fc=Questions.getReponsesCtrl(q);
	if(fc.getFieldType().equals(HtmlControlType.khcRadioList)){
		fc.setValue(reponse.getId()+"");
	}else{
		fc.setValue(values+";"+reponse.getId()+"");
	}
	sReponse= fc+"";
	out.print(sReponse);
	out.print(KoHttp.kajaxIncludes(request));
	out.print("<script>Forms.DOM.onReady(function(){Forms.Utils.fireEvent($('"+fc.getId()+"'), 'change');});</script>");
}else{
	out.print("Erreur de saisie");
}
%>