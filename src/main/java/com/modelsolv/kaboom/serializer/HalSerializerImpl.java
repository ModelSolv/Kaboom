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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

import org.apache.commons.lang3.StringUtils;

import com.modelsolv.kaboom.model.canonical.CanonicalDataType;
import com.modelsolv.kaboom.model.canonical.Cardinality;
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

	// Object count, used to test for cyclical embedding scenarios.
	private int objectCount = 0;
	private int maxObjectCount;

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
		objectCount = 0;
		// The object bound to this resource. .
		Object obj = res.getCanonicalObject();
		// The resource data model, with property exclusions and reference
		// treatments.
		ResourceDataModel rdm = res.getResourceDefinition()
				.getResourceDataModel();
		Representation rep = createNewRepresentation(res);
		buildObjectRepresentation(rep, obj, reader, rdm, new Stack<Object>());
		return rep.toString(halFormat);
	}

	@Override
	public String serialize(Object obj, CanonicalObjectReader reader,
			ResourceDataModel rdm) {
		objectCount = 0;
		// The HAL representation we're going to build.
		Representation rep = createNewRepresentation(obj, reader, rdm);
		buildObjectRepresentation(rep, obj, reader, rdm, new Stack<Object>());
		return rep.toString(halFormat);
	}

	private boolean buildObjectRepresentation(Representation rep, Object obj,
			CanonicalObjectReader reader, ResourceDataModel model,
			Stack<Object> objectStack) {
		if ((maxObjectCount >= 1) && (++objectCount >= maxObjectCount)) {
			throw new RuntimeException(
					"Object graph has exceeded the maximum object count.  Suspected cycle in resource embedding.");
		}
		if (objectStack.contains(obj)) {
			// object is already rendered; rendering again would create an
			// embedding cycle.
			return false;
		}
		objectStack.push(obj);
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
					if (targetObject instanceof Iterable<?>) {
						Iterable<?> targetCollection = (Iterable<?>) targetObject;
						for (Object targetElement : targetCollection) {
							Representation embeddedRep = createNewRepresentation(
									targetElement, reader, embeddedModel);
							if (buildObjectRepresentation(embeddedRep,
									targetElement, reader,
									refEmbed.getEmbeddedModel(), objectStack)) {
								rep.withRepresentation(refEmbed.getName(),
										embeddedRep);
							}
						}
					} else {
						Representation embeddedRep = createNewRepresentation(
								targetObject, reader, embeddedModel);

						if (buildObjectRepresentation(embeddedRep,
								targetObject, reader,
								refEmbed.getEmbeddedModel(), objectStack)) {
							rep.withRepresentation(refEmbed.getName(),
									embeddedRep);
						}
					}
				}
			}
		}
		return true;
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

	public int getMaxObjectCount() {
		return maxObjectCount;
	}

	public void setMaxObjectCount(int objectCount) {
		this.maxObjectCount = objectCount;
	}

	public HalSerializerImpl withMaxObjectCount(int objectCount) {
		setMaxObjectCount(objectCount);
		return this;
	}
}
