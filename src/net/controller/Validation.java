package net.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ko.converters.KoDateConverter;
import net.ko.dao.DatabaseDAOObjectUtils;
import net.ko.framework.Ko;
import net.ko.framework.KoHttp;
import net.ko.http.servlets.KSAction;
import net.ko.kobject.KListObject;
import net.ko.kobject.KRecordStatus;
import net.ko.ksql.KParameterizedInstruction;
import net.models.KQuestion;
import net.models.KQuestionnaire;
import net.models.KReponse;
import net.models.KReponsemultiple;
import net.models.KReponseunique;

/**
 * Servlet implementation class Validation
 */
@WebServlet("/Validation")
public class Validation extends KSAction {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Validation() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		init(request, response);
		KQuestionnaire questionnaire = new KQuestionnaire();

		if (!DatabaseDAOObjectUtils.exists(questionnaire, Ko.kdatabase(), "email='" + request.getParameter("email") + "'")) {
			update(questionnaire, request);
			Ko.getDao(KQuestionnaire.class).create(questionnaire);
		} else {
			update(questionnaire, request);
			questionnaire.toUpdate();
			Ko.getDao(KQuestionnaire.class).update(questionnaire);
			KParameterizedInstruction sql = new KParameterizedInstruction("`", "reponseunique", "idQuestionnaire=" + questionnaire.getId());
			KListObject<KReponseunique> reponsesU = Ko.getDao(KReponseunique.class).readAll(sql);
			reponsesU.setRecordStatus(KRecordStatus.rsDelete);
			Ko.getDao(KReponseunique.class).update(reponsesU);
			sql = new KParameterizedInstruction("`", "reponsemultiple", "idQuestionnaire=" + questionnaire.getId());
			KListObject<KReponsemultiple> reponsesM = Ko.getDao(KReponsemultiple.class).readAll(sql);
			reponsesM.setRecordStatus(KRecordStatus.rsDelete);
			Ko.getDao(KReponsemultiple.class).update(reponsesU);
			out.print("<p>Supression des réponses précédentes.</p>");

		}
		out.print("<h3>Enregistrement des réponses :</h3><ul>");
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			String idQuestionStr = name.replaceAll("\\D+", "");
			if (!"".equals(idQuestionStr)) {
				try {
					int idQuestion = Integer.valueOf(idQuestionStr);
					insertReponse(idQuestion, questionnaire, name, request);
				} catch (Exception e) {
				}
			}
		}
		out.print("</ul>");
		out.print("<h2>Merci de votre participation !</h2><a class='btn close'>Consulter les résultats de l'enquête</a>");
		out.print(KoHttp.kajaxIncludes(request));
	}

	private void insertReponse(int idQuestion, KQuestionnaire questionnaire, String name, HttpServletRequest request) {
		int idQuestionnaire = Integer.valueOf(questionnaire.getId() + "");
		KQuestion question = Ko.getDao(KQuestion.class).readById(idQuestion);
		out.print("<li>" + question);
		if (name.startsWith("Ouverte")) {
			KReponseunique reponse = new KReponseunique();
			reponse.setIdQuestion(idQuestion);
			reponse.setIdQuestionnaire(idQuestionnaire);
			reponse.setReponse(request.getParameter(name));
			Ko.getDao(KReponseunique.class).create(reponse);
			out.print("<ul><li>" + reponse.getReponse() + "</li></ul>");
		} else if (name.startsWith("Multiple")) {
			out.print("<ul>");
			String[] values = request.getParameter(name).split(";");
			for (String value : values) {
				if (value.replaceAll("\\D+", "").equals(value) && !"".equals(value)) {
					KReponsemultiple reponseMultiple = new KReponsemultiple();
					reponseMultiple.setIdQuestionnaire(idQuestionnaire);
					reponseMultiple.setIdReponse(Integer.valueOf(value));
					Ko.getDao(KReponsemultiple.class).create(reponseMultiple);
					KReponse reponse = Ko.getDao(KReponse.class).readById(reponseMultiple.getIdReponse());
					out.print("<li>" + reponse + "</li>");
				}
			}
			out.print("</ul>");
		}
		out.print("</li>");
	}

	private void update(KQuestionnaire questionnaire, HttpServletRequest request) {
		try {
			questionnaire.setDNaiss(KoDateConverter.valueOf(request.getParameter("dNaiss")));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		questionnaire.setEmail(request.getParameter("email"));
		questionnaire.setNom(request.getParameter("nom"));
		questionnaire.setPrenom(request.getParameter("prenom"));
	}

}
