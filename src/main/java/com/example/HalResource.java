package com.example;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.modelsolv.kaboom.rdm.CanonicalObject;
import com.modelsolv.kaboom.rdm.RDMProperty;
import com.modelsolv.kaboom.rdm.RDMReferenceProperty;
import com.modelsolv.kaboom.rdm.ReferenceEmbed;
import com.modelsolv.kaboom.rdm.ReferenceLink;
import com.modelsolv.kaboom.rdm.Resource;
import com.modelsolv.kaboom.rdm.ResourceDataModel;
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

	private String buildResourceRepresentation() {
		RepresentationFactory representationFactory = new StandardRepresentationFactory()
				.withFlag(RepresentationFactory.PRETTY_PRINT);

		// The resource we're going to serialize, probably passed in as a method or ctor param.
		Resource res = null;
		// The object bound to this resource.  A POJO wrapped to provide easey property access.
		CanonicalObject root = res.getBoundObject();
		// The resource data model, with property exclusions and reference treatments.
		ResourceDataModel model = res.getDataModel();
		// The HAL representation we're going to build.
		Representation rep = representationFactory.newRepresentation(res
				.getURI());
		buildObjectRepresentation(rep, root, model);
		return rep.toString();
	}

	private void buildObjectRepresentation(Representation rep, CanonicalObject obj, ResourceDataModel model) {
		List<RDMProperty> props = model.getIncludedProperties();
		for (RDMProperty prop : props) {
			if(!(prop instanceof RDMReferenceProperty)) {
				// primitive field, just render it.
				rep.withProperty(prop.getName(), obj.getPropertyValue(prop));
			} else {
				// it's a reference, we have to see how to treat it.
				RDMReferenceProperty refProp = (RDMReferenceProperty) prop;
				if(refProp instanceof ReferenceLink) {
					// render a link
					buildLink(rep, obj, (ReferenceLink) refProp);
				} else {
					// render an embedded object
					ReferenceEmbed refEmbed = (ReferenceEmbed) refProp;
					Representation embeddedRep = representationFactory.newRepresentation();
					buildObjectRepresentation(embeddedRep, refEmbed.getTargetObject(), refEmbed.getEmbeddedModel());
					rep.withRepresentation(refEmbed.getName(), embeddedRep);
				}
			}
		}
		
	}

	private void buildLink(Representation rep, CanonicalObject obj,
			ReferenceLink refLink) {
		// build the outer link structure, to contain the refLink + decorations
		// TODO: Maybe only do this if there are included properties in the refLink.
		Representation refLinkRep = representationFactory.newRepresentation();
		// add included properties, if any
		CanonicalObject linkedObj = refLink.getTargetObject();
		for (RDMProperty prop : refLink.getIncludedProperties()) {
			refLinkRep.withProperty(prop.getName(), linkedObj.getPropertyValue(prop));
		}
		// now add the link
		refLinkRep.withLink(refLink.getLinkRelation(), refLink.getTargetResource().getURI());
		// add the link structure to the parent structure.
		rep.withRepresentation(refLink.getName(), refLinkRep);
	}
}
