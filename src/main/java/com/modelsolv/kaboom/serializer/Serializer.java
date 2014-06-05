package com.modelsolv.kaboom.serializer;

import com.modelsolv.kaboom.model.rdm.ObjectResource;
import com.modelsolv.kaboom.model.rdm.ResourceDataModel;
import com.modelsolv.kaboom.object.CanonicalObjectReader;

public interface Serializer {
	
	public String serialize(Object obj, CanonicalObjectReader reader, ResourceDataModel rdm);
	
	public String serialize(ObjectResource res, CanonicalObjectReader reader);

}
