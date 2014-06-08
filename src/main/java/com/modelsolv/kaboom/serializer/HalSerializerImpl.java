package com.modelsolv.kaboom.serializer;

import org.apache.commons.lang3.StringUtils;

import com.modelsolv.kaboom.model.canonical.CanonicalDataType;
import com.modelsolv.kaboom.model.resource.ObjectResource;
import com.modelsolv.kaboom.model.resource.ObjectResourceDefinition;
import com.modelsolv.kaboom.model.resource.ObjectResourceDefinitionRegistry;
import com.modelsolv.kaboom.model.resource.RDMProperty;
import com.modelsolv.kaboom.model.resource.RDMReferenceProperty;
import com.modelsolv.kaboom.model.resource.ReferenceEmbed;
import com.modelsolv.kaboom.model.resource.ReferenceLink;
import com.modelsolv.kaboom.model.resource.ResourceDataModel;
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
	public String serialize(ObjectResource res, CanonicalObjectReader reader) {
		// The object bound to this resource. .
		Object obj = res.getCanonicalObject();
		// The resource data model, with property exclusions and reference
		// treatments.
		ResourceDataModel rdm = res.getResourceDefinition()
				.getResourceDataModel();
		Representation rep = createNewRepresentation(res);
		buildObjectRepresentation(rep, obj, reader, rdm);
		return rep.toString(halFormat);
	}

	@Override
	public String serialize(Object obj, CanonicalObjectReader reader,
			ResourceDataModel rdm) {
		// The HAL representation we're going to build.
		Representation rep = representationFactory.newRepresentation();
		buildObjectRepresentation(rep, obj, reader, rdm);
		return rep.toString(halFormat);
	}

	private void buildObjectRepresentation(Representation rep, Object obj,
			CanonicalObjectReader reader, ResourceDataModel model) {
		Iterable<RDMProperty> props = model.getIncludedProperties();
		for (RDMProperty prop : props) {
			if (!(prop instanceof RDMReferenceProperty)) {
				// primitive field, just render it.
				rep.withProperty(prop.getName(),
						reader.getPropertyValue(obj, prop));
			} else {
				// it's a reference, we have to see how to treat it.
				if (prop instanceof ReferenceLink) {
					// render a link
					buildLink(rep, obj, reader, (ReferenceLink) prop);
				} else {
					// render an embedded object
					ReferenceEmbed refEmbed = (ReferenceEmbed) prop;
					ResourceDataModel embeddedModel = refEmbed
							.getEmbeddedModel();
					Object targetObject = reader
							.getPropertyValue(obj, refEmbed);
					Representation embeddedRep = createNewRepresentation(
							targetObject, reader, embeddedModel);
					buildObjectRepresentation(embeddedRep, targetObject,
							reader, refEmbed.getEmbeddedModel());
					rep.withRepresentation(refEmbed.getName(), embeddedRep);
				}
			}
		}
	}

	private void buildLink(Representation rep, Object obj,
			CanonicalObjectReader reader, ReferenceLink refLink) {

		Object targetObj = reader.getPropertyValue(obj, refLink);
		CanonicalDataType cdt = refLink.getCDMProperty().getTargetDataType();
		ObjectResourceDefinition ord = ObjectResourceDefinitionRegistry.INSTANCE
				.getResourceDefinition(cdt);
		ObjectResource targetResource = ord.getResource(targetObj, reader);
		if (refLink.isDecorated()) {
			// Render decorated link in HAL as an embedded object.
			Representation refLinkRep = representationFactory
					.newRepresentation(targetResource.getURI());
			// add included properties
			for (RDMProperty prop : refLink.getIncludedProperties()) {
				refLinkRep.withProperty(prop.getName(),
						reader.getPropertyValue(targetObj, prop));
			}
			// embed the resource representation
			rep.withRepresentation(refLink.getName(), refLinkRep);
		} else {
			// Render naked link
			rep.withLink(refLink.getName(), targetResource.getURI());
		}
	}

	private Representation createNewRepresentation(Object canonicalObject,
			CanonicalObjectReader reader, ResourceDataModel rdm) {
		ObjectResourceDefinitionRegistry registry = ObjectResourceDefinitionRegistry.INSTANCE;
		ObjectResourceDefinition resourceDef = registry
				.getResourceDefinition(rdm.getCanonicalDataType());
		if (resourceDef != null) {
			return createNewRepresentation(resourceDef.getResource(
					canonicalObject, reader));
		} else {
			return representationFactory.newRepresentation();
		}
	}

	private Representation createNewRepresentation(ObjectResource res) {
		if (res.getURI() == null) {
			return representationFactory.newRepresentation();
		} else {
			return representationFactory.newRepresentation(res.getURI());
		}
	}

}
