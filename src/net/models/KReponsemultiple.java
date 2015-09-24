package net.models;

import net.ko.kobject.KObject;
import net.ko.persistence.annotation.Entity;
import net.ko.persistence.annotation.Id;
import net.ko.persistence.annotation.Table;

/**
 * Classe KReponsemultiple
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "reponsemultiple")
public class KReponsemultiple extends KObject {
	@Id
	private int idQuestionnaire;
	@Id
	private int idReponse;
	private KQuestionnaire questionnaire;
	private KReponse reponse;

	public KReponsemultiple() {
		super();
		// belongsTo(KQuestionnaire.class);
		belongsTo(KReponse.class);
	}

	/**
	 * return the value of idQuestionnaire
	 * 
	 * @return idQuestionnaire
	 */
	public int getIdQuestionnaire() {
		return this.idQuestionnaire;
	}

	/**
	 * return the value of idReponse
	 * 
	 * @return idReponse
	 */
	public int getIdReponse() {
		return this.idReponse;
	}

	/**
	 * return the value of questionnaire
	 * 
	 * @return questionnaire
	 */
	public KQuestionnaire getQuestionnaire() {
		return this.questionnaire;
	}

	/**
	 * return the value of reponse
	 * 
	 * @return reponse
	 */
	public KReponse getReponse() {
		return this.reponse;
	}

	/**
	 * set the value of idQuestionnaire
	 * 
	 * @param aIdQuestionnaire
	 */
	public void setIdQuestionnaire(int aIdQuestionnaire) {
		this.idQuestionnaire = aIdQuestionnaire;
	}

	/**
	 * set the value of idReponse
	 * 
	 * @param aIdReponse
	 */
	public void setIdReponse(int aIdReponse) {
		this.idReponse = aIdReponse;
	}

	/**
	 * set the value of questionnaire
	 * 
	 * @param aQuestionnaire
	 */
	public void setQuestionnaire(KQuestionnaire aQuestionnaire) {
		this.questionnaire = aQuestionnaire;
	}

	/**
	 * set the value of reponse
	 * 
	 * @param aReponse
	 */
	public void setReponse(KReponse aReponse) {
		this.reponse = aReponse;
	}

	@Override
	public String toString() {
		return " [idQuestionnaire] = " + idQuestionnaire + " [idReponse] = " + idReponse;
	}
}