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
package com.modelsolv.kaboom.serializer;

import static com.modelsolv.kaboom.model.canonical.PrimitiveDataType.BOOLEAN;
import static com.modelsolv.kaboom.model.canonical.PrimitiveDataType.DATE;
import static com.modelsolv.kaboom.model.canonical.PrimitiveDataType.DECIMAL;
import static com.modelsolv.kaboom.model.canonical.PrimitiveDataType.INTEGER;
import static com.modelsolv.kaboom.model.canonical.PrimitiveDataType.STRING;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.modelsolv.kaboom.model.canonical.CDMFactory;
import com.modelsolv.kaboom.model.canonical.CDMReferenceProperty;
import com.modelsolv.kaboom.model.canonical.CanonicalDataType;
import com.modelsolv.kaboom.model.canonical.Cardinality;
import com.modelsolv.kaboom.model.resource.ObjectResource;
import com.modelsolv.kaboom.model.resource.ObjectResourceDefinition;
import com.modelsolv.kaboom.model.resource.ObjectResourceDefinitionRegistry;
import com.modelsolv.kaboom.model.resource.RDMFactory;
import com.modelsolv.kaboom.model.resource.ResourceDataModel;
import com.modelsolv.kaboom.object.beanImpl.CanonicalObjectBeanReader;
import com.modelsolv.kaboom.testModels.Address;
import com.modelsolv.kaboom.testModels.Company;
import com.modelsolv.kaboom.testModels.Person;
import com.modelsolv.kaboom.testModels.TaxFiling;

public class SerializerTest {
	private CDMFactory cdmFactory;
	private RDMFactory rdmFactory;
	private ObjectResourceDefinitionRegistry registry = ObjectResourceDefinitionRegistry.INSTANCE;

	private CanonicalDataType taxFilingType;
	private ResourceDataModel taxFilingRDM;
	private ObjectResourceDefinition taxFilingORD;

	private CanonicalDataType personType;
	private ResourceDataModel personRDM;
	private ObjectResourceDefinition personORD;

	private CanonicalDataType companyType;
	private ResourceDataModel companyRDM;
	private ObjectResourceDefinition companyORD;

	private CanonicalDataType addressType;
	private ResourceDataModel addressRDM;

	@Before
	public void setUp() throws Exception {
		// buildResourceDataModel();
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Serialize a single object to a HAL JSON, and make sure it serialized
	 * correctly.
	 */
	@Test
	public void testSerializeSingleObject() {
		buildResourceDataModel();
		Address address = buildAddress("123 Sesame Street", null,
				"Ferris Park", "MI", "88717");

		// use the model to serialize to HAL
		Serializer serializer = getSerializer();
		String message = serializer.serialize(address,
				new CanonicalObjectBeanReader(), addressRDM);
		assertFalse(StringUtils.isEmpty(message));
		System.out.println(message);
		JsonNode root = parseJson(message);
		assertEquals("Ferris Park", root.get("city").asText());
		assertEquals("88717", root.get("postalCode").asText());
	}

	/**
	 * Serialize a minimal object graph to a HAL JSON, and make sure it
	 * serialized correctly.
	 */
	@Test
	public void testSerializeLinkedObject() {
		buildResourceDataModel();
		TaxFiling filing = buildTaxFiling();

		// use the model to serialize to HAL
		Serializer serializer = getSerializer();
		String message = serializer.serialize(filing,
				new CanonicalObjectBeanReader(), taxFilingRDM);
		verifyTaxFilingMessage(message, false, false, false, true);
	}

	private void verifyTaxFilingMessage(String message, boolean resourceRoot,
			boolean linkedReference, boolean embeddedResource,
			boolean checkMultiValued) {
		assertFalse(StringUtils.isEmpty(message));
		System.out.println(message);
		JsonNode root = parseJson(message);
		assertEquals("IRS", root.get("jurisdiction").asText());
		assertEquals("1234", root.get("filingID").asText());
		if (resourceRoot) {
			// check the self-link
			assertEquals("http://modelsolv.com/taxblaster/api/taxFilings/1234",
					root.at("/_links/self/href").asText());
		}
		if (linkedReference) {
			// Verify the reference link to Person.
			assertEquals(
					"http://modelsolv.com/taxBlaster/api/people/555-33-5577",
					root.at("/_links/taxpayer/href").asText());
		} else {
			// Verify the embedded Person resource.
			// try pointer expression
			// JsonNode personNode = root.get("_embedded").get("taxpayer");
			JsonNode personNode = root.at("/_embedded/taxpayer");
			assertNotNull(personNode);
			assertEquals("McDermott", personNode.get("lastName").asText());
			if (checkMultiValued) {
				assertEquals("Paul E. McDermott", personNode
						.at("/otherNames/0").asText());
				assertEquals("Paul Edward McDermott",
						personNode.at("/otherNames/1").asText());
				assertEquals("123 Sesame Street",
						personNode.at("/_embedded/addresses/0/street1")
								.asText());
				assertEquals("425 Dobbs Industrial Court",
						personNode.at("/_embedded/addresses/1/street1")
								.asText());
			}
			if (embeddedResource) {
				// Verify that the reference is to a resource, having a
				// self-link.
				JsonNode personSelfLink = root
						.at("/_embedded/taxpayer/_links/self/href");
				assertEquals(
						"http://modelsolv.com/taxBlaster/api/people/555-33-5577",
						personSelfLink.asText());
			}
		}
	}

	/**
	 * Serialize a minimal resource graph to a HAL JSON, and make sure it
	 * serialized correctly. This exercises the default linking behavior on the
	 * referenced object: the reference is to a canonical data type that has a
	 * registered resource, so it will be interpreted as a ReferenceLink.
	 */
	@Test
	public void testSerializeLinkedResource() {
		buildResourceDataModel();

		TaxFiling filing = buildTaxFiling();
		// Register both resource definitions, so Person will be linked by
		// default.
		registerTaxFilingORD();
		registerPersonORD();

		// Create the root resource, serialize to HAL
		ObjectResource taxFilingResource = taxFilingORD.getResource(filing,
				new CanonicalObjectBeanReader());
		Serializer serializer = getSerializer();
		String message = serializer.serialize(taxFilingResource,
				new CanonicalObjectBeanReader());
		verifyTaxFilingMessage(message, true, true, false, true);
	}

	/**
	 * Serialize a minimal object graph to a HAL JSON. This exercises default
	 * linking behavior on the root object: the root object is of a canonical
	 * data type that has a registered resource, so it should automatically have
	 * a self-link.
	 */
	@Test
	public void testSerializeObjectAsResource() {
		buildResourceDataModel();

		TaxFiling filing = buildTaxFiling();
		// Register the TaxFiling resource definition, so the root object will
		// be recognized as a resource having a URI and self-link.
		registerTaxFilingORD();

		// Serialize the root object to HAL
		Serializer serializer = getSerializer();
		String message = serializer.serialize(filing,
				new CanonicalObjectBeanReader(), taxFilingRDM);
		// Root is a resource, reference is an embedded object.
		verifyTaxFilingMessage(message, true, false, false, false);
	}

	/**
	 * Serialize a minimal object graph to a HAL JSON, with a reference
	 * overridden from link to resource.
	 */
	@Test
	public void testSerializeEmbeddedResource() {
		buildResourceDataModel();

		TaxFiling filing = buildTaxFiling();
		taxFilingRDM.includingProperties("currency", "filingID", "grossIncome",
				"jurisdiction", "period", "taxLiability", "year")
				.withReferenceEmbed(
						rdmFactory.createReferenceEmbed(
								(CDMReferenceProperty) taxFilingType
										.getProperty("taxpayer"))
								.withEmbeddedDataModel(
										personRDM.includingProperties(
												"taxpayerID", "lastName")));
		// Register both resource definitions, so Person will be linked by
		// default.
		registerTaxFilingORD();
		registerPersonORD();

		// Create the root resource, serialize to HAL
		ObjectResource taxFilingResource = taxFilingORD.getResource(filing,
				new CanonicalObjectBeanReader());
		Serializer serializer = getSerializer();
		String message = serializer.serialize(taxFilingResource,
				new CanonicalObjectBeanReader());
		// Root
		verifyTaxFilingMessage(message, true, false, true, false);
		// Verify embedded data model
		JsonNode root = parseJson(message);
		JsonNode person = root.at("/_embedded/taxpayer");
		assertNotNull(person);
		assertEquals("555-33-5577", person.get("taxpayerID").asText());
		assertEquals("McDermott", person.get("lastName").asText());
		assertNull(person.get("firstName"));
		assertNull(person.get("_embedded"));
	}

	/**
	 * Serialize to HAL with explicit includedProperties. Make sure that the
	 * excluded properties are not there.
	 */
	@Test
	public void testIncludedProperties() {
		buildResourceDataModel();

		TaxFiling filing = buildTaxFiling();
		// exclude taxpayer, currency, grossIncome, period
		taxFilingRDM.includingProperties("filingID", "jurisdiction",
				"taxLiability", "year");

		Serializer serializer = getSerializer();
		String message = serializer.serialize(filing,
				new CanonicalObjectBeanReader(), taxFilingRDM);

		assertFalse(StringUtils.isEmpty(message));
		System.out.println(message);
		JsonNode root = parseJson(message);
		assertEquals("IRS", root.get("jurisdiction").asText());
		assertEquals("1234", root.get("filingID").asText());
		assertNull(root.get("taxpayer"));
		assertNull(root.get("currency"));
		assertNull(root.get("_links"));
		assertNull(root.get("_embedded"));
	}

	private JsonNode parseJson(String json) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonFactory factory = mapper.getFactory();
			JsonParser jp = factory.createParser(json);
			return mapper.readTree(jp);
		} catch (Exception e) {
			throw new RuntimeException(
					"Failed to parse JSON returned from serializer.", e);
		}
	}

	private void registerTaxFilingORD() {
		// Build Object Resource definitions.
		taxFilingORD = rdmFactory
				.createObjectResourceDefinition(
						"http://modelsolv.com/taxblaster/api/taxFilings/{id}",
						taxFilingRDM)
				.withName("TaxFilingResource")
				.withTemplateParameter("id",
						taxFilingType.getProperty("filingID"));
		registry.registerDefinition(taxFilingORD);
	}

	private void registerPersonORD() {
		personORD = rdmFactory
				.createObjectResourceDefinition(
						"http://modelsolv.com/taxBlaster/api/people/{id}",
						personRDM)
				.withName("PersonResource")
				.withTemplateParameter("id",
						personType.getProperty("taxpayerID"));
		registry.registerDefinition(personORD);
	}

	private void buildResourceDataModel() {
		// TODO inject using Guice?
		cdmFactory = CDMFactory.INSTANCE;
		rdmFactory = RDMFactory.INSTANCE;

		// Define canonical model
		addressType = cdmFactory.createDataType("Address")
				.withPrimitive("street1", STRING)
				.withPrimitive("street2", STRING).withPrimitive("city", STRING)
				.withPrimitive("stateOrProvince", STRING)
				.withPrimitive("postalCode", STRING)
				.withPrimitive("country", STRING);

		companyType = cdmFactory.createDataType("Company")
				.withPrimitive("companyID", STRING)
				.withPrimitive("EIN", STRING)
				.withPrimitive("companyName", STRING)
				.withPrimitive("form", STRING).withPrimitive("active", BOOLEAN);

		personType = cdmFactory
				.createDataType("Person")
				.withPrimitive("taxpayerID", STRING)
				.withPrimitive("firstName", STRING)
				.withPrimitive("lastName", STRING)
				.withPrimitive("otherNames", STRING, Cardinality.ZERO_OR_MORE)
				.withReference("addresses", addressType,
						Cardinality.ZERO_OR_MORE)
				.withReference("employer", companyType);

		// Add inverse reference.
		companyType.withReference("employees", personType,
				Cardinality.ZERO_OR_MORE);
		CDMReferenceProperty companyEmployees = (CDMReferenceProperty) companyType.getProperty("employees");
		CDMReferenceProperty personEmployer = (CDMReferenceProperty) personType.getProperty("employer");
		companyEmployees.setInverseProperty(personEmployer);
		personEmployer.setInverseProperty(companyEmployees);

		taxFilingType = cdmFactory.createDataType("TaxFiling")
				.withPrimitive("filingID", STRING)
				.withReference("taxpayer", personType)
				.withPrimitive("jurisdiction", STRING)
				.withPrimitive("year", DATE).withPrimitive("period", INTEGER)
				.withPrimitive("currency", STRING)
				.withPrimitive("grossIncome", DECIMAL)
				.withPrimitive("taxLiability", DECIMAL);

		// define a data model for a single "Address" entity
		addressRDM = rdmFactory.createResourceDataModel(addressType);
		personRDM = rdmFactory.createResourceDataModel(personType);
		taxFilingRDM = rdmFactory.createResourceDataModel(taxFilingType);
	}

	/**
	 * create an object of a class compatible with with the Address data model
	 * 
	 * @return
	 */
	private Address buildAddress(String street1, String street2, String city,
			String state, String zip) {
		Address address = new Address();
		address.setStreet1(street1);
		address.setStreet2(street2);
		address.setCity(city);
		address.setStateOrProvince(state);
		address.setPostalCode(zip);
		return address;
	}

	private TaxFiling buildTaxFiling() {
		TaxFiling filing = new TaxFiling();
		filing.setFilingID("1234");
		filing.setTaxpayer(buildPerson());
		filing.setCurrency("USD");
		filing.setGrossIncome(BigDecimal.valueOf(115000));
		filing.setJurisdiction("IRS");
		filing.setPeriod(0);
		filing.setTaxLiability(BigDecimal.valueOf(18500));
		filing.setYear(getXmlDate());
		return filing;
	}

	private Person buildPerson() {
		Person p = new Person();
		p.setTaxpayerID("555-33-5577");
		p.setFirstName("Paul");
		p.setLastName("McDermott");
		p.getOtherNames().add("Paul E. McDermott");
		p.getOtherNames().add("Paul Edward McDermott");
		p.getAddresses().add(
				buildAddress("123 Sesame Street", null, "Ferris Park", "MI",
						"88717"));
		p.getAddresses().add(
				buildAddress("425 Dobbs Industrial Court", "Suite 360",
						"Ferris Park", "MI", "88718"));
		// create reference cycle
		p.setEmployer(buildCompany().withEmployee(p));
		return p;
	}

	private Company buildCompany() {
		Company c = new Company();
		c.setActive(true);
		c.setCompanyID("978234");
		c.setCompanyName("Taxomatic, Inc.");
		c.setEIN("27-3611418");
		c.setForm("C");
		return c;
	}

	private XMLGregorianCalendar getXmlDate() {
		try {
			XMLGregorianCalendar date = DatatypeFactory.newInstance()
					.newXMLGregorianCalendarDate(2013, 12, 31, -6);
			return date;
		} catch (Exception e) {
			throw new RuntimeException("Could not build XMLGregorianCalendar.",
					e);
		}
	}
	
	private Serializer getSerializer() {
		return new HalSerializerImpl().withMaxObjectCount(200);
	}
}
