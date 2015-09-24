package net.models;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
import net.ko.persistence.annotation.Entity;
import net.ko.persistence.annotation.Table;

/**
 * Classe KCategorie
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "categorie")
public class KCategorie extends KObject {
	private String Libelle;
	private KListObject<KQuestion> questions;

	public KCategorie() {
		super();
		// hasMany(KQuestion.class);
	}

	/**
	 * return the value of Libelle
	 * 
	 * @return Libelle
	 */
	public String getLibelle() {
		return this.Libelle;
	}

	/**
	 * return the value of questions
	 * 
	 * @return questions
	 */
	public KListObject<KQuestion> getQuestions() {
		return this.questions;
	}

	/**
	 * set the value of Libelle
	 * 
	 * @param aLibelle
	 */
	public void setLibelle(String aLibelle) {
		this.Libelle = aLibelle;
	}

	/**
	 * set the value of questions
	 * 
	 * @param aQuestions
	 */
	public void setQuestions(KListObject<KQuestion> aQuestions) {
		this.questions = aQuestions;
	}

	@Override
	public String toString() {
		return Libelle;
	}
}