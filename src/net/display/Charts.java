package net.display;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import net.ko.controller.KObjectController;
import net.ko.db.KDataBase;
import net.ko.displays.KObjectDisplay;
import net.ko.framework.Ko;
import net.ko.http.views.KFieldControl;
import net.ko.http.views.KHtmlFieldControl;
import net.ko.http.views.KPageList;
import net.ko.kobject.KObject;
import net.ko.utils.KStrings;
import net.models.KChart;

public class Charts extends KObjectDisplay {

	@Override
	public KFieldControl getControl(KObject ko, String memberName, KObjectController koc, HttpServletRequest request) {
		KChart chart = (KChart) ko;
		KHtmlFieldControl fc = (KHtmlFieldControl) super.getControl(ko, memberName, koc, request);
		if ("viewName".equals(memberName)) {
			KDataBase db = Ko.kdatabase();
			ResultSet tables;
			try {
				tables = db.getMetaData().getTables(db.getBaseName(), null, "%", null);
				KStrings ret = new KStrings();
				while (tables.next()) {
					String tableName = tables.getString("TABLE_NAME");
					if (tableName.startsWith("v_"))
						ret.put(tableName, tableName);
				}
				fc.setListObject(ret);
				fc.setRequired(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if ("options".equals(memberName)) {
			if (chart.getOptions() == null || "".equals(chart.getOptions()))
				fc.setValue("{}");
		}
		return fc;
	}

	@Override
	public String showInList(KObject ko, String memberName, HttpServletRequest request) {
		String result = super.showInList(ko, memberName, request);
		if ("update".equals(memberName)) {
			result = "<a class='btn update' id='update-" + ko.getId() + "' title='Modifier le graphique'><span class='glyphicon glyphicon-edit' aria-hidden='true'></span> </a>";
		} else if ("delete".equals(memberName)) {
			result = "<a class='btn delete' id='delete-" + ko.getId() + "' title='Supprimer le graphique'><span onclick='return false;' class='glyphicon glyphicon-remove' aria-hidden='true'></span> </a>";
		}
		return result;
	}

	@Override
	public String getZone(String zoneName, KPageList list, HttpServletRequest request) {
		String result = super.getZone(zoneName, list, request);
		if ("btnAdd".equals(zoneName)) {
			result = "<a class='btn add' title='Ajouter un graphique'><span class='glyphicon glyphicon-plus' aria-hidden='true'></span> Ajouter un graphique...</a>";
		} else if ("ckPreview".equals(zoneName)) {
			result = "<input type='checkbox' id='ckPreview' name='ckPreview'><label for='ckPreview'> Aperçu du graphique</label>";
		} else if ("btAll".equals(zoneName)) {
			result = "<a class='btn all' id='btAll' title='Afficher le rapport'><span class='glyphicon glyphicon-plus' aria-hidden='true'></span> Afficher le rapport</a>";
		}
		return result;
	}

	@Override
	public String getCaption(KObject ko, String memberName) {
		String result = super.getCaption(ko, memberName);
		if ("update".equals(memberName) || "delete".equals(memberName))
			result = "";
		return result;
	}

}
