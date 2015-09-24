package net.models.tests;

import net.models.*;
import net.ko.cache.KCache;
import net.ko.kobject.KListObject;
import net.ko.framework.KoSession;
import net.ko.utils.KScriptTimer;
import net.ko.framework.Ko;

public class Test_questionnaire_KCategorie {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Ko.useCache=true;
		KCache.loadAllCache();

		Ko.kstart();

		KScriptTimer.start();
		KScriptTimer.start("KCategorie");
		KListObject<KCategorie> categories=(KListObject<KCategorie>) KoSession.kloadMany(KCategorie.class);
		KScriptTimer.stop("KCategorie");
		System.out.println(categories);

		KScriptTimer.stop();
		Ko.cacheShutdown();
	}
	

}