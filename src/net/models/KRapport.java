package net.models;

import net.ko.kobject.KObject;
import net.ko.persistence.annotation.Entity;
import net.ko.persistence.annotation.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "rapport")
public class KRapport extends KObject {
	private String titre;
	private String viewSql;

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getViewSql() {
		return viewSql;
	}

	public void setViewSql(String viewSql) {
		this.viewSql = viewSql;
	}

	@Override
	public String toString() {
		return titre;
	}
}
