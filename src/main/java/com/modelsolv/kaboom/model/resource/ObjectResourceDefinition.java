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
import com.modelsolv.kaboom.object.CanonicalObjectReader;

/**
 * A ResourceDefinition that is bound to a ResourceDataModel. An
 * ObjectResourceDefinition defines a class of Resource instances that are bound
 * to an Object confirming to the ResourceDataModel, presumably by conforming to
 * the underlying canonical data type definition.
 * 
 * @author Ted
 * 
 */
public interface ObjectResourceDefinition extends ResourceDefinition {

	// write-once properties, should be passed in ctor
	public String getURITemplate();

	/**
	 * @return the ResourceDataModel bound to this class of resources.
	 */
	public ResourceDataModel getResourceDataModel();

	// read-writable properties

	public String getName();

	public void setName(String name);

	public ObjectResourceDefinition withName(String name);

	public void bindTemplateParameter(String parameter, CDMProperty property);
	
	public ObjectResourceDefinition withTemplateParameter(String parameter, CDMProperty property);

	/**
	 * Factory method to create or retrieve an individual Resource bound to the
	 * canonicalObject. The canonicalObject must confirm to the
	 * ResourceDataModel. This method is responsible for populating the resource
	 * URI and any other required properties of the Resource.
	 * 
	 * @param canonicalObject
	 * @return
	 */
	public ObjectResource getResource(Object canonicalObject, CanonicalObjectReader reader);

}
