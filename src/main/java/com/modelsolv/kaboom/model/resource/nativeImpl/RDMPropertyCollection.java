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
package com.modelsolv.kaboom.model.resource.nativeImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.UnexpectedTypeException;

import org.apache.commons.collections.iterators.EmptyIterator;

import com.modelsolv.kaboom.model.canonical.CDMPrimitiveProperty;
import com.modelsolv.kaboom.model.canonical.CDMProperty;
import com.modelsolv.kaboom.model.canonical.CDMReferenceProperty;
import com.modelsolv.kaboom.model.canonical.CanonicalDataType;
import com.modelsolv.kaboom.model.resource.ObjectResourceDefinition;
import com.modelsolv.kaboom.model.resource.ObjectResourceDefinitionRegistry;
import com.modelsolv.kaboom.model.resource.RDMPrimitiveProperty;
import com.modelsolv.kaboom.model.resource.RDMProperty;
import com.modelsolv.kaboom.model.resource.ReferenceEmbed;
import com.modelsolv.kaboom.model.resource.ReferenceLink;

// helper class for classes that contain a collection of RDMProperties
class RDMPropertyCollection implements Iterable<RDMProperty> {

	private List<RDMProperty> rdmProperties;

	public RDMPropertyCollection withProperties(CDMProperty... properties) {
		for (CDMProperty property : properties) {
			addCDMProperty(property);
		}
		return this;
	}

	public RDMPropertyCollection withProperties(Iterable<CDMProperty> properties) {
		for (CDMProperty property : properties) {
			addCDMProperty(property);
		}
		return this;
	}

	public RDMPropertyCollection withProperties(CanonicalDataType cdt,
			String... properties) {
		for (String propName : properties) {
			CDMProperty property = cdt.getProperty(propName);
			addCDMProperty(property);
		}
		return this;
	}

	private void addCDMProperty(CDMProperty property) {
		if (property instanceof CDMPrimitiveProperty) {
			addPrimitiveProperty((CDMPrimitiveProperty) property);
		} else if (property instanceof CDMReferenceProperty) {
			CDMReferenceProperty refProp = (CDMReferenceProperty) property;
			CanonicalDataType targetType = refProp.getTargetDataType();
			ObjectResourceDefinition targetResourceDef = ObjectResourceDefinitionRegistry.INSTANCE
					.getResourceDefinition(targetType);
			if (targetResourceDef == null) {
				// there's no resource definition for this data type. Default to
				// embedded representation.
				addReferenceEmbed(refProp);
			} else {
				// if we have a resource definition, default to a reference
				// link.
				addReferenceLink(refProp);
			}
		} else {
			throw new UnexpectedTypeException("Unknown CDMProperty subtype.");
		}
	}

	public void addRDMProperty(RDMProperty property) {
		if (isCDMPropertyIncluded(property.getCDMProperty())) {
			throw new IllegalArgumentException(
					"The canonical property has already been added to the collection.");
		}
		if (rdmProperties == null) {
			rdmProperties = new ArrayList<RDMProperty>();
		}
		rdmProperties.add(property);
	}

	public RDMPrimitiveProperty addPrimitiveProperty(
			CDMPrimitiveProperty property) {
		RDMPrimitiveProperty rdmProp = new RDMPrimitivePropertyImpl(property);
		addRDMProperty(rdmProp);
		return rdmProp;
	}

	public ReferenceLink addReferenceLink(CDMReferenceProperty property) {
		ReferenceLink refLink = new ReferenceLinkImpl(property);
		addRDMProperty(refLink);
		return refLink;
	}

	public ReferenceEmbed addReferenceEmbed(CDMReferenceProperty property) {
		ReferenceEmbed refEmbed = new ReferenceEmbedImpl(property);
		addRDMProperty(refEmbed);
		return refEmbed;

	}

	/**
	 * Returns True iif the property is in the includedProperties list, which
	 * overrides the canonical type's properties list. Otherwise returns false.
	 * Note that this method is not intended to test whether the property will
	 * be included in the resource representation. If the includedProperties
	 * list is null, all properties will be included in the representation, but
	 * this method will still return false.
	 * 
	 * @param property
	 * @return
	 */
	private boolean isCDMPropertyIncluded(CDMProperty cdmProperty) {
		if (rdmProperties == null) {
			return false;
		}
		for (RDMProperty rdmProperty : rdmProperties) {
			if (rdmProperty.getCDMProperty() == cdmProperty) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterator<RDMProperty> iterator() {
		return isEmpty() ? EmptyIterator.INSTANCE : rdmProperties.iterator();
	}

	public boolean isEmpty() {
		return (rdmProperties == null) || (rdmProperties.isEmpty());
	}

}
