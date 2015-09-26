package net.models;

import net.ko.kobject.KObject;
import net.ko.persistence.annotation.Entity;
import net.ko.persistence.annotation.Id;
import net.ko.persistence.annotation.Table;

/**
 * Classe KCharttype
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "charttype")
public class KCharttype extends KObject {
	@Id
	private String name;
	private String title;

	public KCharttype() {
		super();
		//
	}

	/**
	 * return the value of name
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * return the value of title
	 * 
	 * @return title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * set the value of name
	 * 
	 * @param aName
	 */
	public void setName(String aName) {
		this.name = aName;
	}

	/**
	 * set the value of title
	 * 
	 * @param aTitle
	 */
	public void setTitle(String aTitle) {
		this.title = aTitle;
	}

	@Override
	public String toString() {
		return title;
	}
}