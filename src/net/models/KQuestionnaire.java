package net.models;

import java.sql.Date;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
import net.ko.persistence.annotation.Entity;
import net.ko.persistence.annotation.Table;

/**
 * Classe KQuestionnaire
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "questionnaire")
public class KQuestionnaire extends KObject {
	private java.sql.Date dNaiss;
	private String email;
	private String nom;
	private String prenom;
	private KListObject<KReponsemultiple> reponsemultiples;
	private KListObject<KReponseunique> reponseuniques;

	public KQuestionnaire() {
		super();
		dNaiss = Date.valueOf("1996-1-1");
		// hasMany(KReponseunique.class);hasMany(KReponsemultiple.class);
	}

	/**
	 * return the value of dNaiss
	 * 
	 * @return dNaiss
	 */
	public java.sql.Date getDNaiss() {
		return this.dNaiss;
	}

	/**
	 * return the value of email
	 * 
	 * @return email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * return the value of nom
	 * 
	 * @return nom
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * return the value of prenom
	 * 
	 * @return prenom
	 */
	public String getPrenom() {
		return this.prenom;
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
	 * return the value of reponseuniques
	 * 
	 * @return reponseuniques
	 */
	public KListObject<KReponseunique> getReponseuniques() {
		return this.reponseuniques;
	}

	/**
	 * set the value of dNaiss
	 * 
	 * @param aDNaiss
	 */
	public void setDNaiss(java.sql.Date aDNaiss) {
		this.dNaiss = aDNaiss;
	}

	/**
	 * set the value of email
	 * 
	 * @param aEmail
	 */
	public void setEmail(String aEmail) {
		this.email = aEmail;
	}

	/**
	 * set the value of nom
	 * 
	 * @param aNom
	 */
	public void setNom(String aNom) {
		this.nom = aNom;
	}

	/**
	 * set the value of prenom
	 * 
	 * @param aPrenom
	 */
	public void setPrenom(String aPrenom) {
		this.prenom = aPrenom;
	}

	/**
	 * set the value of reponsemultiples
	 * 
	 * @param aReponsemultiples
	 */
	public void setReponsemultiples(KListObject<KReponsemultiple> aReponsemultiples) {
		this.reponsemultiples = aReponsemultiples;
	}

	/**
	 * set the value of reponseuniques
	 * 
	 * @param aReponseuniques
	 */
	public void setReponseuniques(KListObject<KReponseunique> aReponseuniques) {
		this.reponseuniques = aReponseuniques;
	}

	@Override
	public String toString() {
		return " [nom] = " + nom + " [prenom] = " + prenom + " [email] = " + email + " [dNaiss] = " + dNaiss;
	}

}