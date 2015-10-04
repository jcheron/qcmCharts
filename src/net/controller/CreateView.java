package net.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ko.db.KDataBase;
import net.ko.framework.Ko;
import net.ko.http.objects.KRequest;
import net.ko.utils.KString;
import net.models.KRapport;

/**
 * Servlet implementation class CreateView
 */
@WebServlet("/CreateView")
public class CreateView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateView() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = KRequest.GETPOST("idRapport", request);
		if (KString.isNotNull(id)) {
			KRapport rapport = Ko.getDao(KRapport.class).readById(id);
			if (rapport.isLoaded()) {
				KDataBase db = Ko.kdatabase();
				try {
					db.execute("DROP VIEW IF EXISTS activequestionnaire;");
					db.execute("CREATE VIEW activequestionnaire as " + rapport.getViewSql() + ";");
					db.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
