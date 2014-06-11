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

import com.modelsolv.kaboom.model.canonical.CDMProperty;

public interface ReferenceLink extends RDMReferenceProperty {

	/**
	 * The list of included "decorator" properties included in the
	 * ReferenceLink. These may be defined directly in the ReferenceLink, or in
	 * a named LinkDescriptor.
	 * <p>
	 * TODO : These may be modeled as property paths, allowing promotion
	 * of properties through arbitrary levels. We don't want to complicate the
	 * serializer with this, so for now we assume they are direct properties of
	 * the target data type. Implementation should exclude indirect properties
	 * for now.
	 * 
	 * @return
	 */
	public Iterable<RDMProperty> getIncludedProperties();

	public ReferenceLink includingProperties(CDMProperty... properties);
	
	public ReferenceLink includingProperties(String... properties);
	
	/**
	 * Return true if the ReferenceLink has one or more included properties.
	 */
	public boolean isDecorated();

	/**
	 * 
	 * @return the Link Relation if specified, otherwise null
	 */
	public String getLinkRelation();
	
	public void setLinkRelation(String linkRelation);
	
	public ReferenceLink withLinkRelation(String linkRelation);

	/**
	 * The target resource of the link. Will be the default resource for the
	 * referenced data type, unless it's overridden in the ReferenceLink
	 * definition.
	 * 
	 * @return
	 */
	public ObjectResourceDefinition getTargetResourceDefinition();
	
	public void setTargetResourceDefinition(ObjectResourceDefinition resourceDef);
	
	public ReferenceLink withTargetResourceDefinition(ObjectResourceDefinition resourceDef);
	
}
