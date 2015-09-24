package net.models.tests;

import net.models.*;
import net.ko.cache.KCache;
import net.ko.kobject.KListObject;
import net.ko.framework.KoSession;
import net.ko.utils.KScriptTimer;
import net.ko.framework.Ko;

public class Test_questionnaire_KReponsemultiple {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Ko.useCache=true;
		KCache.loadAllCache();

		Ko.kstart();

		KScriptTimer.start();
		KScriptTimer.start("KReponsemultiple");
		KListObject<KReponsemultiple> reponsemultiples=(KListObject<KReponsemultiple>) KoSession.kloadMany(KReponsemultiple.class);
		KScriptTimer.stop("KReponsemultiple");
		System.out.println(reponsemultiples);

		KScriptTimer.stop();
		Ko.cacheShutdown();
	}
	

}