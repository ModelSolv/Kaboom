package com.modelsolv.kaboom.serializer;

import com.modelsolv.kaboom.model.rdm.ResourceDataModel;
import com.modelsolv.kaboom.object.CanonicalObjectReader;

public interface Serializer {
	
	public String serialize(CanonicalObjectReader obj, ResourceDataModel rdm);

}
