package com.modelsolv.kaboom.serializer;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.modelsolv.kaboom.model.rdm.RDMFactory;
import com.modelsolv.kaboom.model.rdm.ReferenceLink;
import com.modelsolv.kaboom.model.rdm.ResourceDataModel;
import com.modelsolv.kaboom.model.rdm.nativeImpl.NativeRDMFactory;
import com.modelsolv.kaboom.object.beanImpl.CanonicalObjectBeanReader;
import com.modelsolv.reprezen.restapi.RestapiFactory;
import com.modelsolv.reprezen.restapi.ZenModel;
import com.modelsolv.reprezen.schemas.taxblasterapi.Address;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SerializerTest {

	private ResourceDataModel addressRDM;
	private ResourceDataModel customerRDM;
	private RDMFactory factory;

	@Before
	public void setUp() throws Exception {
		// buildResourceDataModel();
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Serialize a single object to a HAL JSON, and make sure it serialized correctly.
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
	 * Serialize a minimal object graph to a HAL JSON, and make sure it serialized correctly.
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
		factory = new NativeRDMFactory();

		// define a data model for a single "Address" entity
		// TODO introduce CanonicalDataModel, and make the RDM realized from it.
		addressRDM = factory.createResourceDataModel();
		addressRDM.getIncludedProperties().add(
				factory.createRDMPrimitiveProperty("street1"));
		addressRDM.getIncludedProperties().add(
				factory.createRDMPrimitiveProperty("street2"));
		addressRDM.getIncludedProperties().add(
				factory.createRDMPrimitiveProperty("city"));
		addressRDM.getIncludedProperties().add(
				factory.createRDMPrimitiveProperty("stateOrProvince"));
		addressRDM.getIncludedProperties().add(
				factory.createRDMPrimitiveProperty("postalCode"));
		addressRDM.getIncludedProperties().add(
				factory.createRDMPrimitiveProperty("country"));
		
		// data model for a customer, with a reference to Address
		customerRDM = factory.createResourceDataModel();
		customerRDM.getIncludedProperties().add(
				factory.createRDMPrimitiveProperty("customerID"));
		customerRDM.getIncludedProperties().add(
				factory.createRDMPrimitiveProperty("firstName"));
		customerRDM.getIncludedProperties().add(
				factory.createRDMPrimitiveProperty("lastName"));
		customerRDM.getIncludedProperties().add(
				factory.createRDMPrimitiveProperty("companyName"));
//		// Link to Address
//		ReferenceLink addressLink = factory.createReferenceLink("address");
//		addressLink.
//		customerRDM.getIncludedProperties().add(
//				);
		
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
