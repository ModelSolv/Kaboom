package com.modelsolv.kaboom.testModels;

import java.util.HashSet;
import java.util.Set;

public class Company {
	private String companyID;
	private String EIN;
	private String companyName;
	private String form;
	private Boolean active;
	private Set<Person> employees=new HashSet<Person>();

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
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	public Company withEmployee(Person employee) {
		if(!employees.contains(employee)) {
			employees.add(employee);
		}
		return this;
	}
	
	public Iterable<Person> getEmployees() {
		return employees;
	}
}
