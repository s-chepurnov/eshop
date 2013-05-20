package org.shop.entity;

import java.io.Serializable;

public class Supplier implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String companyName;
	private String address;
	private String url;
	private String contacts;
	private String notes;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Supplier(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return "Supplier [id=" + id + ", companyName=" + companyName
				+ ", address=" + address + ", url=" + url + ", contacts="
				+ contacts + ", notes=" + notes + "]";
	}
}