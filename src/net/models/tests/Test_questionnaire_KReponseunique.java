package net.models.tests;

import net.models.*;
import net.ko.cache.KCache;
import net.ko.kobject.KListObject;
import net.ko.framework.KoSession;
import net.ko.utils.KScriptTimer;
import net.ko.framework.Ko;

public class Test_questionnaire_KReponseunique {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Ko.useCache=true;
		KCache.loadAllCache();

		Ko.kstart();

		KScriptTimer.start();
		KScriptTimer.start("KReponseunique");
		KListObject<KReponseunique> reponseuniques=(KListObject<KReponseunique>) KoSession.kloadMany(KReponseunique.class);
		KScriptTimer.stop("KReponseunique");
		System.out.println(reponseuniques);

		KScriptTimer.stop();
		Ko.cacheShutdown();
	}
	

}