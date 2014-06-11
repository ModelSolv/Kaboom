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

import com.modelsolv.kaboom.model.canonical.CDMPrimitiveProperty;
import com.modelsolv.kaboom.model.canonical.CDMProperty;
import com.modelsolv.kaboom.model.canonical.CDMReferenceProperty;
import com.modelsolv.kaboom.model.canonical.CanonicalDataType;
import com.modelsolv.kaboom.model.resource.RDMPrimitiveProperty;
import com.modelsolv.kaboom.model.resource.RDMProperty;
import com.modelsolv.kaboom.model.resource.ReferenceEmbed;
import com.modelsolv.kaboom.model.resource.ReferenceLink;
import com.modelsolv.kaboom.model.resource.ResourceDataModel;

public class ResourceDataModelImpl implements ResourceDataModel {

	private CanonicalDataType canonicalType;

	private RDMPropertyCollection includedProperties = new RDMPropertyCollection();

	public ResourceDataModelImpl() {
	}

	public ResourceDataModelImpl(CanonicalDataType cdt) {
		setCanonicalDataType(cdt);
	}

	@Override
	public Iterable<RDMProperty> getIncludedProperties() {
		if (!includedProperties.isEmpty()) {
			return includedProperties;
		}
		// If we don't have a list of included properties, return a collection
		// of the canonical data type's properties, wrapped as RDMProperties.
		RDMPropertyCollection rdmProps = new RDMPropertyCollection()
				.withProperties(getCanonicalDataType().getProperties());
		return rdmProps;
	}

	@Override
	public CanonicalDataType getCanonicalDataType() {
		return canonicalType;
	}

	@Override
	public void setCanonicalDataType(CanonicalDataType type) {
		canonicalType = type;
	}

	@Override
	public ResourceDataModel withCanonicalDataType(CanonicalDataType type) {
		setCanonicalDataType(type);
		return this;
	}

	@Override
	public ResourceDataModel includingProperties(CDMProperty... properties) {
		includedProperties.withProperties(properties);
		return this;
	}

	@Override
	public ResourceDataModel includingProperties(String... properties) {
		includedProperties.withProperties(getCanonicalDataType(), properties);
		return this;
	}

	@Override
	public RDMPrimitiveProperty addPrimitiveProperty(
			CDMPrimitiveProperty property) {
		return includedProperties.addPrimitiveProperty(property);
	}

	@Override
	public ResourceDataModel withPrimitiveProperty(RDMPrimitiveProperty property) {
		includedProperties.addRDMProperty(property);
		return this;
	}

	@Override
	public ReferenceLink addReferenceLink(CDMReferenceProperty property) {
		return includedProperties.addReferenceLink(property);
	}

	@Override
	public ResourceDataModel withReferenceLink(ReferenceLink refLink) {
		includedProperties.addRDMProperty(refLink);
		return this;
	}

	@Override
	public ReferenceEmbed addReferenceEmbed(CDMReferenceProperty property) {
		return includedProperties.addReferenceEmbed(property);
	}

	@Override
	public ResourceDataModel withReferenceEmbed(ReferenceEmbed refEmbed) {
		includedProperties.addRDMProperty(refEmbed);
		return this;
	}
}
