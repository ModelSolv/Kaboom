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

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for TaxFiling complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TaxFiling">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="filingID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="jurisdiction" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="year" use="required" type="{http://www.w3.org/2001/XMLSchema}gYear" />
 *       &lt;attribute name="period" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="currency" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="grossIncome" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="taxLiability" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TaxFiling")
public class TaxFiling {

    @XmlAttribute(name = "filingID", required = true)
    protected String filingID;
    protected Person taxpayer;
	@XmlAttribute(name = "jurisdiction", required = true)
    protected String jurisdiction;
    @XmlAttribute(name = "year", required = true)
    @XmlSchemaType(name = "gYear")
    protected XMLGregorianCalendar year;
    @XmlAttribute(name = "period", required = true)
    protected int period;
    @XmlAttribute(name = "currency", required = true)
    protected String currency;
    @XmlAttribute(name = "grossIncome", required = true)
    protected BigDecimal grossIncome;
    @XmlAttribute(name = "taxLiability", required = true)
    protected BigDecimal taxLiability;

    /**
     * Gets the value of the filingID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFilingID() {
        return filingID;
    }

    /**
     * Sets the value of the filingID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFilingID(String value) {
        this.filingID = value;
    }

    public Person getTaxpayer() {
		return taxpayer;
	}

	public void setTaxpayer(Person taxpayer) {
		this.taxpayer = taxpayer;
	}

    /**
     * Gets the value of the jurisdiction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJurisdiction() {
        return jurisdiction;
    }

    /**
     * Sets the value of the jurisdiction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJurisdiction(String value) {
        this.jurisdiction = value;
    }

    /**
     * Gets the value of the year property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getYear() {
        return year;
    }

    /**
     * Sets the value of the year property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setYear(XMLGregorianCalendar value) {
        this.year = value;
    }

    /**
     * Gets the value of the period property.
     * 
     */
    public int getPeriod() {
        return period;
    }

    /**
     * Sets the value of the period property.
     * 
     */
    public void setPeriod(int value) {
        this.period = value;
    }

    /**
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrency(String value) {
        this.currency = value;
    }

    /**
     * Gets the value of the grossIncome property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getGrossIncome() {
        return grossIncome;
    }

    /**
     * Sets the value of the grossIncome property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setGrossIncome(BigDecimal value) {
        this.grossIncome = value;
    }

    /**
     * Gets the value of the taxLiability property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTaxLiability() {
        return taxLiability;
    }

    /**
     * Sets the value of the taxLiability property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTaxLiability(BigDecimal value) {
        this.taxLiability = value;
    }

}
