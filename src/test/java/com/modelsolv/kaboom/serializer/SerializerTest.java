package com.modelsolv.kaboom.serializer;

import static com.modelsolv.kaboom.model.canonical.PrimitiveDataType.DATE;
import static com.modelsolv.kaboom.model.canonical.PrimitiveDataType.DECIMAL;
import static com.modelsolv.kaboom.model.canonical.PrimitiveDataType.INTEGER;
import static com.modelsolv.kaboom.model.canonical.PrimitiveDataType.STRING;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
import com.modelsolv.kaboom.model.canonical.CanonicalDataType;
import com.modelsolv.kaboom.model.canonical.Cardinality;
import com.modelsolv.kaboom.model.resource.RDMFactory;
import com.modelsolv.kaboom.model.resource.ResourceDataModel;
import com.modelsolv.kaboom.object.beanImpl.CanonicalObjectBeanReader;
import com.modelsolv.kaboom.testModels.Address;
import com.modelsolv.kaboom.testModels.Person;
import com.modelsolv.kaboom.testModels.TaxFiling;

public class SerializerTest {

	private ResourceDataModel addressRDM;
	private ResourceDataModel personRDM;
	private ResourceDataModel taxFilingRDM;
	private CDMFactory cdmFactory;
	private RDMFactory rdmFactory;

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
		Address address = buildSimpleAddress();

		// use the model to serialize to HAL
		Serializer serializer = new HalSerializerImpl();
		String message = serializer.serialize(address,
				new CanonicalObjectBeanReader(), addressRDM);
		assertFalse(StringUtils.isEmpty(message));
		System.out.println(message);
		JsonNode root = parseJson(message);
		assertEquals("Hastings On Hudson", root.get("city").asText());
		assertEquals("10706", root.get("postalCode").asText());
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
		Serializer serializer = new HalSerializerImpl();
		String message = serializer.serialize(filing,
				new CanonicalObjectBeanReader(), taxFilingRDM);
		assertFalse(StringUtils.isEmpty(message));
		System.out.println(message);
		JsonNode root = parseJson(message);
		assertEquals("Hastings On Hudson", root.get("city").asText());
		assertEquals("10706", root.get("postalCode").asText());
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

	private void buildResourceDataModel() {
		// TODO inject using Guice?
		cdmFactory = CDMFactory.INSTANCE;
		rdmFactory = RDMFactory.INSTANCE;

		// Define canonical model
		CanonicalDataType addressType = cdmFactory.createDataType("Address")
				.withPrimitive("street1", STRING)
				.withPrimitive("street2", STRING).withPrimitive("city", STRING)
				.withPrimitive("stateOrProvince", STRING)
				.withPrimitive("postalCode", STRING)
				.withPrimitive("country", STRING);

		CanonicalDataType personType = cdmFactory.createDataType("Person")
				.withPrimitive("taxpayerID", STRING)
				.withPrimitive("firstName", STRING)
				.withPrimitive("lastName", STRING);
		// multi-valued fields don't work yet.
		/*
		 * .withReference("addresses", addressType, Cardinality.ZERO_OR_MORE);
		 */

		CanonicalDataType taxFilingType = cdmFactory
				.createDataType("TaxFiling").withPrimitive("filingID", STRING)
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
	private Address buildSimpleAddress() {
		Address address = new Address();
		address.setStreet1("42 Donald Drive");
		address.setCity("Hastings On Hudson");
		address.setStateOrProvince("NY");
		address.setPostalCode("10706");
		return address;
	}

	private TaxFiling buildTaxFiling() {
		TaxFiling filing = new TaxFiling();
		filing.setCurrency("USD");
		filing.setFilingID("1324");
		filing.setGrossIncome(BigDecimal.valueOf(115000));
		filing.setJurisdiction("IRS");
		filing.setPeriod(0);
		filing.setTaxLiability(BigDecimal.valueOf(18500));
		filing.setTaxpayer(buildPerson());
		filing.setYear(getXmlDate());
		return filing;
	}

	private Person buildPerson() {
		Person p = new Person();
		p.setFirstName("Paul");
		p.setLastName("McDermott");
		p.getAddresses().add(buildSimpleAddress());
		return p;
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
}
