package net.models;

import java.sql.Date;

import net.ko.kobject.KObject;
import net.ko.persistence.annotation.Entity;
import net.ko.persistence.annotation.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "groupe")
public class KGroupe extends KObject {
	private Date dNaiss;
	private String libelle;

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return libelle;
	}

	public Date getdNaiss() {
		return dNaiss;
	}

	public void setdNaiss(Date dNaiss) {
		this.dNaiss = dNaiss;
	}
}
