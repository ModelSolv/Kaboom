
package com.modelsolv.reprezen.schemas.taxblasterapi;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.modelsolv.reprezen.schemas.taxblasterapi package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _TaxFilingsResource_QNAME = new QName("http://modelsolv.com/reprezen/schemas/taxblasterapi", "taxFilingsResource");
    private final static QName _IndexResource_QNAME = new QName("http://modelsolv.com/reprezen/schemas/taxblasterapi", "indexResource");
    private final static QName _PeopleResource_QNAME = new QName("http://modelsolv.com/reprezen/schemas/taxblasterapi", "peopleResource");
    private final static QName _PersonResource_QNAME = new QName("http://modelsolv.com/reprezen/schemas/taxblasterapi", "personResource");
    private final static QName _TaxFilingResource_QNAME = new QName("http://modelsolv.com/reprezen/schemas/taxblasterapi", "taxFilingResource");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.modelsolv.reprezen.schemas.taxblasterapi
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Person }
     * 
     */
    public Person createPerson() {
        return new Person();
    }

    /**
     * Create an instance of {@link TaxFilingResource }
     * 
     */
    public TaxFilingResource createTaxFilingResource() {
        return new TaxFilingResource();
    }

    /**
     * Create an instance of {@link TaxFilingResource.Taxpayer }
     * 
     */
    public TaxFilingResource.Taxpayer createTaxFilingResourceTaxpayer() {
        return new TaxFilingResource.Taxpayer();
    }

    /**
     * Create an instance of {@link PeopleResource }
     * 
     */
    public PeopleResource createPeopleResource() {
        return new PeopleResource();
    }

    /**
     * Create an instance of {@link PersonResource }
     * 
     */
    public PersonResource createPersonResource() {
        return new PersonResource();
    }

    /**
     * Create an instance of {@link TaxFilingsResource }
     * 
     */
    public TaxFilingsResource createTaxFilingsResource() {
        return new TaxFilingsResource();
    }

    /**
     * Create an instance of {@link IndexResource }
     * 
     */
    public IndexResource createIndexResource() {
        return new IndexResource();
    }

    /**
     * Create an instance of {@link Address }
     * 
     */
    public Address createAddress() {
        return new Address();
    }

    /**
     * Create an instance of {@link TaxFiling }
     * 
     */
    public TaxFiling createTaxFiling() {
        return new TaxFiling();
    }

    /**
     * Create an instance of {@link Person.AddressList }
     * 
     */
    public Person.AddressList createPersonAddressList() {
        return new Person.AddressList();
    }

    /**
     * Create an instance of {@link Person.LastNameList }
     * 
     */
    public Person.LastNameList createPersonLastNameList() {
        return new Person.LastNameList();
    }

    /**
     * Create an instance of {@link TaxFilingResource.Taxpayer.LastNameList }
     * 
     */
    public TaxFilingResource.Taxpayer.LastNameList createTaxFilingResourceTaxpayerLastNameList() {
        return new TaxFilingResource.Taxpayer.LastNameList();
    }

    /**
     * Create an instance of {@link PeopleResource.AddressList }
     * 
     */
    public PeopleResource.AddressList createPeopleResourceAddressList() {
        return new PeopleResource.AddressList();
    }

    /**
     * Create an instance of {@link PeopleResource.LastNameList }
     * 
     */
    public PeopleResource.LastNameList createPeopleResourceLastNameList() {
        return new PeopleResource.LastNameList();
    }

    /**
     * Create an instance of {@link PersonResource.AddressList }
     * 
     */
    public PersonResource.AddressList createPersonResourceAddressList() {
        return new PersonResource.AddressList();
    }

    /**
     * Create an instance of {@link PersonResource.LastNameList }
     * 
     */
    public PersonResource.LastNameList createPersonResourceLastNameList() {
        return new PersonResource.LastNameList();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TaxFilingsResource }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://modelsolv.com/reprezen/schemas/taxblasterapi", name = "taxFilingsResource")
    public JAXBElement<TaxFilingsResource> createTaxFilingsResource(TaxFilingsResource value) {
        return new JAXBElement<TaxFilingsResource>(_TaxFilingsResource_QNAME, TaxFilingsResource.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IndexResource }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://modelsolv.com/reprezen/schemas/taxblasterapi", name = "indexResource")
    public JAXBElement<IndexResource> createIndexResource(IndexResource value) {
        return new JAXBElement<IndexResource>(_IndexResource_QNAME, IndexResource.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PeopleResource }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://modelsolv.com/reprezen/schemas/taxblasterapi", name = "peopleResource")
    public JAXBElement<PeopleResource> createPeopleResource(PeopleResource value) {
        return new JAXBElement<PeopleResource>(_PeopleResource_QNAME, PeopleResource.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersonResource }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://modelsolv.com/reprezen/schemas/taxblasterapi", name = "personResource")
    public JAXBElement<PersonResource> createPersonResource(PersonResource value) {
        return new JAXBElement<PersonResource>(_PersonResource_QNAME, PersonResource.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TaxFilingResource }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://modelsolv.com/reprezen/schemas/taxblasterapi", name = "taxFilingResource")
    public JAXBElement<TaxFilingResource> createTaxFilingResource(TaxFilingResource value) {
        return new JAXBElement<TaxFilingResource>(_TaxFilingResource_QNAME, TaxFilingResource.class, null, value);
    }

}
