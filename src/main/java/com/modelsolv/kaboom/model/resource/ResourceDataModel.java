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
package com.modelsolv.kaboom.model.resource;

import com.modelsolv.kaboom.model.canonical.CDMPrimitiveProperty;
import com.modelsolv.kaboom.model.canonical.CDMProperty;
import com.modelsolv.kaboom.model.canonical.CDMReferenceProperty;
import com.modelsolv.kaboom.model.canonical.CanonicalDataType;

public interface ResourceDataModel {

	public CanonicalDataType getCanonicalDataType();

	public void setCanonicalDataType(CanonicalDataType type);

	public ResourceDataModel withCanonicalDataType(CanonicalDataType type);

	public Iterable<RDMProperty> getIncludedProperties();

	// Add a list of CDM properties from the CanonicalDataType
	public ResourceDataModel includingProperties(CDMProperty... properties);

	public ResourceDataModel includingProperties(String... properties);

	// Add Primitive Property from CDM Property
	public RDMPrimitiveProperty addPrimitiveProperty(
			CDMPrimitiveProperty property);

	// Add Primitive Property directly, chainable, builder style
	public ResourceDataModel withPrimitiveProperty(
			RDMPrimitiveProperty property);

	// Add ReferenceLink from CDMReferenceProperty
	public ReferenceLink addReferenceLink(CDMReferenceProperty property);

	// Add ReferenceLink Directly, chainable, builder style
	public ResourceDataModel withReferenceLink(ReferenceLink refLink);

	// Add ReferenceEmbed from CDMReferenceProperty
	public ReferenceEmbed addReferenceEmbed(CDMReferenceProperty property);

	// Add ReferenceEmbed Directly, chainable, builder style
	public ResourceDataModel withReferenceEmbed(ReferenceEmbed refEmbed);
}
