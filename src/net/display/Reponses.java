package net.display;

import javax.servlet.http.HttpServletRequest;

import net.ko.controller.KObjectController;
import net.ko.displays.KObjectDisplay;
import net.ko.http.views.KFieldControl;
import net.ko.http.views.KHtmlFieldControl;
import net.ko.kobject.KObject;
import net.ko.types.HtmlControlType;
import net.models.KQuestion;

public class Reponses extends KObjectDisplay {

	@Override
	public KFieldControl getReadOnlyControl(KObject ko, String memberName, KObjectController koc, HttpServletRequest request) {
		KHtmlFieldControl fc = (KHtmlFieldControl) super.getReadOnlyControl(ko, memberName, koc, request);
		// fc.setCaption("");

		if ("reponses".equals(memberName)) {
			KQuestion q = (KQuestion) ko;
			fc.setListObject(q.getReponses());
			fc.setValue("");
			fc.setFieldType(HtmlControlType.khcCheckBox);
		}
		return fc;
	}

}
