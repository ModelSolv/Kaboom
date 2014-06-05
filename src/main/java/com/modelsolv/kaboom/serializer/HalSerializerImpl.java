package com.modelsolv.kaboom.serializer;

import java.util.List;

import com.modelsolv.kaboom.model.rdm.RDMProperty;
import com.modelsolv.kaboom.model.rdm.RDMReferenceProperty;
import com.modelsolv.kaboom.model.rdm.ReferenceEmbed;
import com.modelsolv.kaboom.model.rdm.ReferenceLink;
import com.modelsolv.kaboom.model.rdm.ObjectResource;
import com.modelsolv.kaboom.model.rdm.ResourceDataModel;
import com.modelsolv.kaboom.object.CanonicalObjectReader;
import com.theoryinpractise.halbuilder.api.Representation;
import com.theoryinpractise.halbuilder.api.RepresentationFactory;
import com.theoryinpractise.halbuilder.standard.StandardRepresentationFactory;

public class HalSerializerImpl extends AbstractSerializerImpl {
	
	private RepresentationFactory representationFactory;
	private String halFormat = RepresentationFactory.HAL_JSON;
	
	public HalSerializerImpl() {
		representationFactory = new StandardRepresentationFactory()
		.withFlag(RepresentationFactory.PRETTY_PRINT);

	}

	public HalSerializerImpl(String halFormat) {
		this.halFormat = halFormat;
		representationFactory = new StandardRepresentationFactory()
		.withFlag(RepresentationFactory.PRETTY_PRINT);

	}

	@Override
	public String serialize(Object obj, CanonicalObjectReader reader, ResourceDataModel rdm) {
		// The HAL representation we're going to build.
		Representation rep = representationFactory.newRepresentation();
		buildObjectRepresentation(rep, obj, reader, rdm);
		return rep.toString(halFormat);
	}

	private void buildObjectRepresentation(Representation rep, Object obj, CanonicalObjectReader reader, ResourceDataModel model) {
		List<RDMProperty> props = model.getIncludedProperties();
		for (RDMProperty prop : props) {
			if(!(prop instanceof RDMReferenceProperty)) {
				// primitive field, just render it.
				rep.withProperty(prop.getName(), reader.getPropertyValue(obj, prop));
			} else {
				// it's a reference, we have to see how to treat it.
				if(prop instanceof ReferenceLink) {
					// render a link
					buildLink(rep, reader, (ReferenceLink) prop);
				} else {
					// render an embedded object
					ReferenceEmbed refEmbed = (ReferenceEmbed) prop;
					Representation embeddedRep = representationFactory.newRepresentation();
					buildObjectRepresentation(embeddedRep, refEmbed.getTargetObject(), reader, refEmbed.getEmbeddedModel());
					rep.withRepresentation(refEmbed.getName(), embeddedRep);
				}
			}
		}
		
	}

	private void buildLink(Representation rep, CanonicalObjectReader reader,
			ReferenceLink refLink) {
		// build the outer link structure, to contain the refLink + decorations
		// TODO: Maybe only do this if there are included properties in the refLink.
		Representation refLinkRep = representationFactory.newRepresentation();
		// add included properties, if any
		Object linkedObj = refLink.getTargetObject();
		for (RDMProperty prop : refLink.getIncludedProperties()) {
			refLinkRep.withProperty(prop.getName(), reader.getPropertyValue(linkedObj, prop));
		}
		// now add the link
		refLinkRep.withLink(refLink.getLinkRelation(), refLink.getTargetResource().getURI());
		// add the link structure to the parent structure.
		rep.withRepresentation(refLink.getName(), refLinkRep);
	}
	

}
