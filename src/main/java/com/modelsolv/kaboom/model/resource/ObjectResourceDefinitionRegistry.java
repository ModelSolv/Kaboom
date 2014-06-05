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

	// TODO: Check proper use of Singleton pattern, consider DI/Resource
	// Locator.
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
