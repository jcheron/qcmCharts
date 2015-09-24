package net.models;

import net.ko.kobject.KObject;
import net.ko.persistence.annotation.Entity;
import net.ko.persistence.annotation.Table;
import net.ko.persistence.annotation.Id;


/**
* Classe KReponseunique
*/
@SuppressWarnings("serial")
@Entity
@Table(name="reponseunique")
public class KReponseunique extends KObject {
	@Id
	private int idQuestion;
	@Id
	private int idQuestionnaire;
	private String reponse;
	private KQuestion question;
	private KQuestionnaire questionnaire;

	public KReponseunique() {
		super();
		//belongsTo(KQuestionnaire.class);belongsTo(KQuestion.class);
	}
	/**
	 * return the value of idQuestion
	 * @return idQuestion
	 */
	public int getIdQuestion(){
		return this.idQuestion;
	}
	/**
	 * return the value of idQuestionnaire
	 * @return idQuestionnaire
	 */
	public int getIdQuestionnaire(){
		return this.idQuestionnaire;
	}
	/**
	 * return the value of reponse
	 * @return reponse
	 */
	public String getReponse(){
		return this.reponse;
	}
	/**
	 * return the value of question
	 * @return question
	 */
	public KQuestion getQuestion(){
		return this.question;
	}
	/**
	 * return the value of questionnaire
	 * @return questionnaire
	 */
	public KQuestionnaire getQuestionnaire(){
		return this.questionnaire;
	}

	/**
	 * set the value of idQuestion
	 * @param aIdQuestion
	 */
	public void setIdQuestion(int aIdQuestion){
		this.idQuestion=aIdQuestion;
	}
	/**
	 * set the value of idQuestionnaire
	 * @param aIdQuestionnaire
	 */
	public void setIdQuestionnaire(int aIdQuestionnaire){
		this.idQuestionnaire=aIdQuestionnaire;
	}
	/**
	 * set the value of reponse
	 * @param aReponse
	 */
	public void setReponse(String aReponse){
		this.reponse=aReponse;
	}
	/**
	 * set the value of question
	 * @param aQuestion
	 */
	public void setQuestion(KQuestion aQuestion){
		this.question=aQuestion;
	}
	/**
	 * set the value of questionnaire
	 * @param aQuestionnaire
	 */
	public void setQuestionnaire(KQuestionnaire aQuestionnaire){
		this.questionnaire=aQuestionnaire;
	}
	@Override
	public String toString() {
		return " [reponse] = " + reponse+" [idQuestion] = " + idQuestion+" [idQuestionnaire] = " + idQuestionnaire;
	}
}