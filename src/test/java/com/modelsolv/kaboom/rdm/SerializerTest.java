package com.modelsolv.kaboom.rdm;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.Main;
import com.modelsolv.kaboom.rdm.beanImpl.RDMBeanFactory;

import static org.junit.Assert.assertEquals;

public class SerializerTest {

    private ResourceDataModel rdm;
	private RDMFactory factory;


    @Before
    public void setUp() throws Exception {
    	buildResourceDataModel();
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
    
    private void buildResourceDataModel() {
    	// TODO inject using Guice?
    	factory = new RDMBeanFactory();

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
    	
    	// use the model to serialize to HAL
//    	Serializer serializer = new HalSerializer();
//    	serializer.serializeObject
    }
}
