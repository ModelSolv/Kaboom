package com.modelsolv.kaboom.testModels;

public class Company {
	private String companyID;
	private String EIN;
	private String companyName;
	private String form;
	private Boolean Active;

	public String getCompanyID() {
		return companyID;
	}

	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}

	public String getEIN() {
		return EIN;
	}

	public void setEIN(String eIN) {
		EIN = eIN;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public Boolean getActive() {
		return Active;
	}

	public void setActive(Boolean active) {
		Active = active;
	}
}
