package com.modelsolv.kaboom.model.rdm;

import java.net.URI;

import com.modelsolv.kaboom.object.CanonicalObjectReader;

public interface Resource {
	
	public Object getCanonicalObject();

	public ResourceDataModel getResourceDataModel();
	
	public URI getURI();
}
