package com.modelsolv.kaboom.serializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
import static com.modelsolv.kaboom.model.canonical.PrimitiveDataType.STRING;
import static com.modelsolv.kaboom.model.canonical.PrimitiveDataType.BOOLEAN;
import static com.modelsolv.kaboom.model.canonical.PrimitiveDataType.DATE;
import static com.modelsolv.kaboom.model.canonical.PrimitiveDataType.FLOAT;
import static com.modelsolv.kaboom.model.canonical.PrimitiveDataType.INTEGER;
import com.modelsolv.kaboom.model.resource.RDMFactory;
import com.modelsolv.kaboom.model.resource.ResourceDataModel;
import com.modelsolv.kaboom.object.beanImpl.CanonicalObjectBeanReader;
import com.modelsolv.reprezen.schemas.taxblasterapi.Address;

public class SerializerTest {

	private ResourceDataModel addressRDM;
	private ResourceDataModel customerRDM;
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
		CanonicalDataType address = cdmFactory.createDataType("Address")
				.withPrimitive("street1", STRING)
				.withPrimitive("street2", STRING)
				.withPrimitive("city", STRING)
				.withPrimitive("stateOrProvince", STRING)
				.withPrimitive("postalCode", STRING)
				.withPrimitive("country", STRING);

		// define a data model for a single "Address" entity
		addressRDM = rdmFactory.createResourceDataModel(address);

		// data model for a customer, with a reference to Address
//		customerRDM = rdmFactory.createResourceDataModel();
//		customerRDM.getIncludedProperties().add(
//				rdmFactory.createRDMPrimitiveProperty("customerID"));
//		customerRDM.getIncludedProperties().add(
//				rdmFactory.createRDMPrimitiveProperty("firstName"));
//		customerRDM.getIncludedProperties().add(
//				rdmFactory.createRDMPrimitiveProperty("lastName"));
//		customerRDM.getIncludedProperties().add(
//				rdmFactory.createRDMPrimitiveProperty("companyName"));

		// // Link to Address
		// ReferenceLink addressLink = factory.createReferenceLink("address");
		// addressLink.
		// customerRDM.getIncludedProperties().add(
		// );

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

}
