package net.models.tests;

import net.models.*;
import net.ko.cache.KCache;
import net.ko.kobject.KListObject;
import net.ko.framework.KoSession;
import net.ko.utils.KScriptTimer;
import net.ko.framework.Ko;

public class Test_questionnaire {

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
KScriptTimer.start("KQuestion");
		KListObject<KQuestion> questions=(KListObject<KQuestion>) KoSession.kloadMany(KQuestion.class);
		KScriptTimer.stop("KQuestion");
		System.out.println(questions);
KScriptTimer.start("KQuestionnaire");
		KListObject<KQuestionnaire> questionnaires=(KListObject<KQuestionnaire>) KoSession.kloadMany(KQuestionnaire.class);
		KScriptTimer.stop("KQuestionnaire");
		System.out.println(questionnaires);
KScriptTimer.start("KReponse");
		KListObject<KReponse> reponses=(KListObject<KReponse>) KoSession.kloadMany(KReponse.class);
		KScriptTimer.stop("KReponse");
		System.out.println(reponses);
KScriptTimer.start("KReponsemultiple");
		KListObject<KReponsemultiple> reponsemultiples=(KListObject<KReponsemultiple>) KoSession.kloadMany(KReponsemultiple.class);
		KScriptTimer.stop("KReponsemultiple");
		System.out.println(reponsemultiples);
KScriptTimer.start("KReponseunique");
		KListObject<KReponseunique> reponseuniques=(KListObject<KReponseunique>) KoSession.kloadMany(KReponseunique.class);
		KScriptTimer.stop("KReponseunique");
		System.out.println(reponseuniques);

		KScriptTimer.stop();
		Ko.cacheShutdown();
	}
	

}