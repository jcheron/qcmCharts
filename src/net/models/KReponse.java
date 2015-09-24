package net.models;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
import net.ko.persistence.annotation.Entity;
import net.ko.persistence.annotation.Table;

/**
 * Classe KReponse
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "reponse")
public class KReponse extends KObject {
	private int idQuestion;
	private String libelle;
	private int ordre;
	private KQuestion question;
	private KListObject<KReponsemultiple> reponsemultiples;

	public KReponse() {
		super();
		// hasMany(KReponsemultiple.class);
		belongsTo(KQuestion.class);
	}

	/**
	 * return the value of idQuestion
	 * 
	 * @return idQuestion
	 */
	public int getIdQuestion() {
		return this.idQuestion;
	}

	/**
	 * return the value of libelle
	 * 
	 * @return libelle
	 */
	public String getLibelle() {
		return this.libelle;
	}

	/**
	 * return the value of ordre
	 * 
	 * @return ordre
	 */
	public int getOrdre() {
		return this.ordre;
	}

	/**
	 * return the value of question
	 * 
	 * @return question
	 */
	public KQuestion getQuestion() {
		return this.question;
	}

	/**
	 * return the value of reponsemultiples
	 * 
	 * @return reponsemultiples
	 */
	public KListObject<KReponsemultiple> getReponsemultiples() {
		return this.reponsemultiples;
	}

	/**
	 * set the value of idQuestion
	 * 
	 * @param aIdQuestion
	 */
	public void setIdQuestion(int aIdQuestion) {
		this.idQuestion = aIdQuestion;
	}

	/**
	 * set the value of libelle
	 * 
	 * @param aLibelle
	 */
	public void setLibelle(String aLibelle) {
		this.libelle = aLibelle;
	}

	/**
	 * set the value of ordre
	 * 
	 * @param aOrdre
	 */
	public void setOrdre(int aOrdre) {
		this.ordre = aOrdre;
	}

	/**
	 * set the value of question
	 * 
	 * @param aQuestion
	 */
	public void setQuestion(KQuestion aQuestion) {
		this.question = aQuestion;
	}

	/**
	 * set the value of reponsemultiples
	 * 
	 * @param aReponsemultiples
	 */
	public void setReponsemultiples(KListObject<KReponsemultiple> aReponsemultiples) {
		this.reponsemultiples = aReponsemultiples;
	}

	@Override
	public String toString() {
		return libelle;
	}
}