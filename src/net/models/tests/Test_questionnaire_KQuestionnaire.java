package net.models.tests;

import net.models.*;
import net.ko.cache.KCache;
import net.ko.kobject.KListObject;
import net.ko.framework.KoSession;
import net.ko.utils.KScriptTimer;
import net.ko.framework.Ko;

public class Test_questionnaire_KQuestionnaire {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Ko.useCache=true;
		KCache.loadAllCache();

		Ko.kstart();

		KScriptTimer.start();
		KScriptTimer.start("KQuestionnaire");
		KListObject<KQuestionnaire> questionnaires=(KListObject<KQuestionnaire>) KoSession.kloadMany(KQuestionnaire.class);
		KScriptTimer.stop("KQuestionnaire");
		System.out.println(questionnaires);

		KScriptTimer.stop();
		Ko.cacheShutdown();
	}
	

}