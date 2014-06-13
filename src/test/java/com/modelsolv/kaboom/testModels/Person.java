/*******************************************************************************
 * Copyright (c) 2014 ModelSolv, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     ModelSolv, Inc. - initial API and implementation.
 *******************************************************************************/

package com.modelsolv.kaboom.testModels;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

public class Person {

    protected String taxpayerID;
	protected String lastName;
    protected String firstName;
	protected List<Address> addresses = new ArrayList<Address>();
	protected Company employer;
	protected List<String> otherNames = new ArrayList<String>();

	/**s
     * Gets the value of the addressList property.
     * 
     * @return
     *     possible object is
     *     {@link Person.AddressList }
     *     
     */
    public List<Address> getAddresses() {
        return addresses;
    }

    /**
     * Gets the value of the lastNameList property.
     * 
     * @return
     *     possible object is
     *     {@link Person.LastNameList }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastNameList property.
     * 
     * @param value
     *     allowed object is
     *     {@link Person.LastNameList }
     *     
     */
    public void setLastName(String value) {
        this.lastName= value;
    }

    /**
     * Gets the value of the taxpayerID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxpayerID() {
        return taxpayerID;
    }

    /**
     * Sets the value of the taxpayerID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxpayerID(String value) {
        this.taxpayerID = value;
    }

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }
    
    public Company getEmployer() {
		return employer;
	}

	public void setEmployer(Company employer) {
		this.employer = employer;
	}
	
	public List<String> getOtherNames() {
		return otherNames;
	}

}
