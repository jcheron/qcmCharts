package net.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ko.framework.Ko;
import net.ko.http.objects.KRequest;
import net.ko.http.servlets.KSAction;
import net.ko.ksql.KParameterizedInstruction;
import net.models.KChart;

@WebServlet("/Order")
public class Order extends KSAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private KChart chart;

	public Order() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		this.init(request, response);
		String id = request.getParameter("request.id");
		String op = KRequest.GETPOST("sens", request, "up");
		if (id != null) {
			id = id.replace(op + "-", "");
			chart = Ko.getDao(KChart.class).readById(id);
			if (chart.isLoaded()) {
				move("up".equals(op));
			}
		}
	}

	private void move(boolean sens) {
		String operateur = "<=";
		String asc = "";
		if (!sens)
			operateur = ">=";
		if (sens)
			asc = " DESC";
		int tmpOrdre = chart.getOrdre();
		KChart otherChart = new KChart();
		KParameterizedInstruction sql = new KParameterizedInstruction("`", "ordre!=" + chart.getOrdre() + " AND ordre" + operateur + chart.getOrdre());
		sql.setOrderBy("ordre" + asc);
		Ko.getDao(KChart.class).read(otherChart, sql, 0);
		if (otherChart.isLoaded()) {
			chart.setOrdre(otherChart.getOrdre());
			chart.toUpdate();
			otherChart.setOrdre(tmpOrdre);
			otherChart.toUpdate();
			Ko.getDao(KChart.class).update(otherChart);
			Ko.getDao(KChart.class).update(chart);
		}
	}

}
