package net.models.tests;

import net.models.*;
import net.ko.cache.KCache;
import net.ko.kobject.KListObject;
import net.ko.framework.KoSession;
import net.ko.utils.KScriptTimer;
import net.ko.framework.Ko;

public class Test_questionnaire_KReponse {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Ko.useCache=true;
		KCache.loadAllCache();

		Ko.kstart();

		KScriptTimer.start();
		KScriptTimer.start("KReponse");
		KListObject<KReponse> reponses=(KListObject<KReponse>) KoSession.kloadMany(KReponse.class);
		KScriptTimer.stop("KReponse");
		System.out.println(reponses);

		KScriptTimer.stop();
		Ko.cacheShutdown();
	}
	

}