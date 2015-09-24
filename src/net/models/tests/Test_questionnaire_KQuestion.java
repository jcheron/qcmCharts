package net.models.tests;

import net.models.*;
import net.ko.cache.KCache;
import net.ko.kobject.KListObject;
import net.ko.framework.KoSession;
import net.ko.utils.KScriptTimer;
import net.ko.framework.Ko;

public class Test_questionnaire_KQuestion {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Ko.useCache=true;
		KCache.loadAllCache();

		Ko.kstart();

		KScriptTimer.start();
		KScriptTimer.start("KQuestion");
		KListObject<KQuestion> questions=(KListObject<KQuestion>) KoSession.kloadMany(KQuestion.class);
		KScriptTimer.stop("KQuestion");
		System.out.println(questions);

		KScriptTimer.stop();
		Ko.cacheShutdown();
	}
	

}