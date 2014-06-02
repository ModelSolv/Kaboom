package com.modelsolv.kaboom.serializer;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.Main;
import com.modelsolv.kaboom.model.rdm.RDMFactory;
import com.modelsolv.kaboom.model.rdm.ResourceDataModel;
import com.modelsolv.kaboom.model.rdm.nativeImpl.NativeRDMFactory;
import com.modelsolv.kaboom.object.beanImpl.CanonicalObjectBeanReader;
import com.modelsolv.reprezen.restapi.RestapiFactory;
import com.modelsolv.reprezen.restapi.ZenModel;
import com.modelsolv.reprezen.schemas.taxblasterapi.Address;

import static org.junit.Assert.assertEquals;

public class SerializerTest {

    private ResourceDataModel rdm;
	private RDMFactory factory;


    @Before
    public void setUp() throws Exception {
    //	buildResourceDataModel();
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testSerializeSingleObject() {

    }
    
    @Test
    public void testZenModel() {
    	RestapiFactory factory = RestapiFactory.eINSTANCE;
    	ZenModel foo = factory.createZenModel();
    }

    private void buildResourceDataModel() {
    	// TODO inject using Guice?
    	factory = new NativeRDMFactory();

    	// define a data model for a single "Address" entity
    	// TODO introduce CanonicalDataModel, and make the RDM realized from it.
    	ResourceDataModel rdm = factory.createResourceDataModel();
    	rdm.getIncludedProperties().add(factory.createRDMPrimitiveProperty("street1"));
    	rdm.getIncludedProperties().add(factory.createRDMPrimitiveProperty("street2"));
    	rdm.getIncludedProperties().add(factory.createRDMPrimitiveProperty("city"));
    	rdm.getIncludedProperties().add(factory.createRDMPrimitiveProperty("stateOrProvince"));
    	rdm.getIncludedProperties().add(factory.createRDMPrimitiveProperty("postalCode"));
    	rdm.getIncludedProperties().add(factory.createRDMPrimitiveProperty("country"));
    	
    	// create an object of a class compatible with with the Address data model
    	Address address = new Address();
    	address.setStreet1("42 Donald Drive");
    	address.setCity("Hastings On Hudson");
    	address.setStateOrProvince("NY");
    	address.setPostalCode("10706");
    	
    	// Adapt it to CanonicalObject for reflective access
    	CanonicalObjectBeanReader canonicalAddress = new CanonicalObjectBeanReader(address);
    	
    	// use the model to serialize to HAL
    	Serializer serializer = new HalSerializerImpl();
    	String message = serializer.serialize(canonicalAddress, rdm);
    }
    
}
