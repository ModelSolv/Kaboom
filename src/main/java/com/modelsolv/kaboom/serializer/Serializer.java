package com.modelsolv.kaboom.serializer;

import com.modelsolv.kaboom.model.resource.ObjectResource;
import com.modelsolv.kaboom.model.resource.ResourceDataModel;
import com.modelsolv.kaboom.object.CanonicalObjectReader;

public interface Serializer {

	/**
	 * Serialize a canonical object. This is basic serialization without a URI
	 * associated with the root resource. However, referenced objects that have
	 * associated ResourceDefinitions through the
	 * ObjectResourceDefinitionRegistry will be rendered with their URIs.
	 * 
	 * @param obj
	 * @param reader
	 * @param rdm
	 * @return
	 */
	public String serialize(Object obj, CanonicalObjectReader reader,
			ResourceDataModel rdm);

	/**
	 * Serialize an ObjectResource. The resource URI, canonical object, and
	 * resource data model used for serialization are all extracted from the
	 * resource.
	 * 
	 * @param res
	 * @param reader
	 * @return
	 */
	public String serialize(ObjectResource res, CanonicalObjectReader reader);

}
