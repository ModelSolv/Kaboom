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

import com.modelsolv.kaboom.model.canonical.CanonicalDataType;
import com.modelsolv.kaboom.model.resource.nativeImpl.ObjectResourceDefinitionRegistryImpl;

/**
 * A registry of ObjectResourceDefinitions, allowing lookup by the name or by
 * the associated ResourceDataModel.
 * 
 * @author Ted
 * 
 */
public interface ObjectResourceDefinitionRegistry {

	// TODO: Check use of Singleton pattern, consider DI/Resource Locator.
	public static ObjectResourceDefinitionRegistry INSTANCE = new ObjectResourceDefinitionRegistryImpl();

	/**
	 * Register a ResourceDataModel. Registering an ObjectResourceDefinition
	 * with a duplicate name, ResourceDataModel or CanonicalDataType will remove
	 * the previous entry from the registry, and add this one in its place.
	 * 
	 * @param rdm
	 * @param definition
	 */
	public void registerDefinition(ObjectResourceDefinition definition);

	/**
	 * Retrieve an ObjectResourceDefinition by its name.
	 * 
	 * @param name
	 * @return
	 */
	public ObjectResourceDefinition getResourceDefinition(String name);

	/**
	 * Retrieve an ObjectResourceDefinition by its associated ResourceDataModel.
	 * 
	 * @param name
	 * @return
	 */
	public ObjectResourceDefinition getResourceDefinition(ResourceDataModel rdm);

	/**
	 * Retrieve an ObjectResourceDefinition by its associated CanonicalDataType.
	 * 
	 * @param name
	 * @return
	 */
	public ObjectResourceDefinition getResourceDefinition(CanonicalDataType canonicalDataType);

}
