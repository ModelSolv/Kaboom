package com.modelsolv.kaboom.model.resource;

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

	/**
	 * @return the ResourceDataModel bound to this class of resources.
	 */
	public ResourceDataModel getResourceDataModel();

	/**
	 * Create or retrieve an individual Resource bound to the canonicalObject.
	 * The canonicalObject must have the properties specified in the
	 * ResourceDataModel. This method is responsible for populating the resource
	 * URI and any other required properties of the Resource.
	 * 
	 * @param canonicalObject
	 * @return
	 */
	public ObjectResource getResource(Object canonicalObject);

}
