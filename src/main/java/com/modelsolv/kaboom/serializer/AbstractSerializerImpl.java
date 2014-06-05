package com.modelsolv.kaboom.serializer;

import com.modelsolv.kaboom.model.resource.ObjectResource;
import com.modelsolv.kaboom.model.resource.ResourceDataModel;
import com.modelsolv.kaboom.object.CanonicalObjectReader;

public abstract class AbstractSerializerImpl implements Serializer {
	
	 @Override
	public String serialize(ObjectResource res, CanonicalObjectReader reader) {
			// The object bound to this resource.  .
			Object obj = res.getCanonicalObject();
			// The resource data model, with property exclusions and reference treatments.
			ResourceDataModel rdm = res.getResourceDefinition().getResourceDataModel();
			return serialize(obj, reader, rdm);
	}

}
