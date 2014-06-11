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


/**
 * <p>Java class for Person complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Person">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="addressList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="address" type="{http://modelsolv.com/reprezen/schemas/taxblasterapi}Address" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="lastNameList">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="item" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="taxpayerID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="firstName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Person", propOrder = {
    "addressList",
    "lastNameList"
})
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
