package com.modelsolv.kaboom.serializer;

import com.modelsolv.kaboom.model.rdm.Resource;
import com.modelsolv.kaboom.model.rdm.ResourceDataModel;
import com.modelsolv.kaboom.object.CanonicalObjectReader;

public interface Serializer {
	
	public String serialize(Object obj, CanonicalObjectReader reader, ResourceDataModel rdm);
	
	public String serialize(Resource res, CanonicalObjectReader reader);

}
