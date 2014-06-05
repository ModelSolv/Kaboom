package com.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.theoryinpractise.halbuilder.api.Representation;
import com.theoryinpractise.halbuilder.api.RepresentationFactory;
import com.theoryinpractise.halbuilder.standard.StandardRepresentationFactory;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("halresource")
public class HalResource {
	
	private RepresentationFactory representationFactory;
	
	public HalResource() {
		representationFactory = new StandardRepresentationFactory()
		.withFlag(RepresentationFactory.PRETTY_PRINT);

	}

	
	/**
	 * Method handling HTTP GET requests. The returned object will be sent to
	 * the client as "text/plain" media type.
	 * 
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		return buildHalSample();
	}

	private String buildHalSample() {

		Representation owner = representationFactory
				.newRepresentation("http://example.com/mike")
				.withLink("td:friend", "http://example.com/mamund")
				.withProperty("name", "Mike").withProperty("age", "36");

		Representation halResource = representationFactory
				.newRepresentation("http://example.com/todo-list")
				.withNamespace("td", "http://example.com/todoapp/rels/{rel}")
				.withLink("/todo-list/search;{searchterm}", "td:search")
				.withLink("/todo-list/description", "td:description")
				.withProperty("created_at", "2010-01-16")
				.withProperty("updated_at", "2010-02-21")
				.withProperty("summary", "An example list")
				.withRepresentation("td:owner", owner);

		String xml = halResource.toString(RepresentationFactory.HAL_XML);
		String json = halResource.toString(RepresentationFactory.HAL_JSON);

		return json;
	}
}
