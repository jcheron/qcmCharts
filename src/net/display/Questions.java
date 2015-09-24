package net.display;

import javax.servlet.http.HttpServletRequest;

import net.ko.displays.KObjectDisplay;
import net.ko.http.objects.KRequest;
import net.ko.http.views.KHtmlFieldControl;
import net.ko.http.views.KPageList;
import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
import net.ko.types.HtmlControlType;
import net.models.KQuestion;
import net.models.KReponse;

public class Questions extends KObjectDisplay {

	@Override
	public String showInList(KObject ko, String memberName, HttpServletRequest request) {
		String s = super.showInList(ko, memberName, request);
		KQuestion q = (KQuestion) ko;
		if ("essai".equals(memberName)) {

			String id = q.getId() + "";
			s = "<div id='frm-reponses-content" + id + "'><div id='reponses-content" + id + "'>";
			s += Questions.getReponsesCtrl(q) + "</div>";
			if (q.getType().endsWith("Ext")) {
				s += "<input placeholder='Ajouter " + q.getLibc() + "...' type='text' id='value-" + id + "' name='value-" + id + "' class='field-add form-control'>&nbsp;"
						+ "<a class='btn add' id='btAdd-" + id + "'><span class='glyphicon glyphicon-plus-sign' aria-hidden='true'></span> Ajouter</a>";
			}
			s += "</div>";
		} else if ("infoBulle".equals(memberName)) {
			if (q.getComplement() != null) {
				s = q.getComplement();
				s = net.ko.http.js.KJavaScript.infoBulle("<span class='glyphicon glyphicon-info-sign' aria-hidden='true'></span>", "<p>" + s + "</p>");
			}
		}
		return s;
	}

	@Override
	public void afterLoading(KListObject<? extends KObject> kl, KPageList list, HttpServletRequest request) {
		super.afterLoading(kl, list, request);
		kl.sortBy("ordre");
	}

	public static KHtmlFieldControl getReponsesCtrl(KQuestion q) {
		HtmlControlType ct = HtmlControlType.khcCheckedList;
		String value = "";
		if (q.getType().startsWith("MultipleUnique"))
			ct = HtmlControlType.khcRadioList;
		else if (q.getType().equals("Ouverte")) {
			ct = HtmlControlType.khcNumber;
			value = "0";

		}

		KHtmlFieldControl fc = new KHtmlFieldControl(q.getLibc() + " :", value, q.getType() + q.getId(), q.getType() + q.getId(), ct);
		fc.setMin(0);
		if (!"Cursus".equals(q.getLibc()))
			fc.setRequired(true);
		KListObject<KReponse> reponses = q.getReponses();
		reponses.sortBy("ordre");
		fc.setListObject(reponses);
		return fc;
	}

	@Override
	public void beforeLoading(Class<? extends KObject> clazz, KPageList list, HttpServletRequest request) {
		int idCategorie = KRequest.GETPOST("idCategorie", request, 1);
		list.setWhere("idCategorie=" + idCategorie);
		super.beforeLoading(clazz, list, request);
	}

	@Override
	public String getZone(String zoneName, KPageList list, HttpServletRequest request) {
		String s = super.getZone(zoneName, list, request);
		if ((zoneName.endsWith("down") && list.getKListObject().count() > 3) || !zoneName.endsWith("down")) {
			if (zoneName.startsWith("btnNext")) {
				if (!"6".equals(request.getParameter("idCategorie"))) {
					s = "<a class='btn btnNext'>Questions suivantes <span class='glyphicon glyphicon-step-forward' aria-hidden='true'></span></a>";
				}
				else {
					s = "<a class='btn btnValidate'><span class='glyphicon glyphicon-ok-sign' aria-hidden='true'></span> Valider les réponses</a>";
				}
			} else if (zoneName.startsWith("btnPrevious")) {
				if (!"1".equals(request.getParameter("idCategorie"))) {
					s = "<a class='btn btnPrevious'><span class='glyphicon glyphicon-step-backward' aria-hidden='true'></span> Questions précédentes</a>";
				} else
					s = "";
			}
		} else
			s = "";
		return s;
	}

}
