package net.models;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
import net.ko.persistence.annotation.Entity;
import net.ko.persistence.annotation.Table;

/**
 * Classe KQuestion
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "question")
public class KQuestion extends KObject {
	private int idCategorie;
	private String libelle;
	private String libc;
	private int ordre;
	private String type;
	private String complement;

	private KCategorie categorie;
	private KListObject<KReponse> reponses;
	private KListObject<KReponseunique> reponseuniques;

	public KQuestion() {
		super();
		// hasMany(KReponseunique.class);
		hasMany(KReponse.class);
		belongsTo(KCategorie.class);
	}

	/**
	 * return the value of idCategorie
	 * 
	 * @return idCategorie
	 */
	public int getIdCategorie() {
		return this.idCategorie;
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
	 * return the value of type
	 * 
	 * @return type
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * return the value of categorie
	 * 
	 * @return categorie
	 */
	public KCategorie getCategorie() {
		return this.categorie;
	}

	/**
	 * return the value of reponses
	 * 
	 * @return reponses
	 */
	public KListObject<KReponse> getReponses() {
		return this.reponses;
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
	 * set the value of idCategorie
	 * 
	 * @param aIdCategorie
	 */
	public void setIdCategorie(int aIdCategorie) {
		this.idCategorie = aIdCategorie;
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
	 * set the value of type
	 * 
	 * @param aType
	 */
	public void setType(String aType) {
		this.type = aType;
	}

	/**
	 * set the value of categorie
	 * 
	 * @param aCategorie
	 */
	public void setCategorie(KCategorie aCategorie) {
		this.categorie = aCategorie;
	}

	/**
	 * set the value of reponses
	 * 
	 * @param aReponses
	 */
	public void setReponses(KListObject<KReponse> aReponses) {
		this.reponses = aReponses;
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
		return libelle + " (" + type + ")";
	}

	public String getLibc() {
		return libc;
	}

	public void setLibc(String libc) {
		this.libc = libc;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}
}