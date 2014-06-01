package com.modelsolv.kaboom.model.rdm;

import java.net.URI;

import com.modelsolv.kaboom.object.CanonicalObject;

public interface Resource {
	
	public CanonicalObject getBoundObject();

	public ResourceDataModel getDataModel();
	
	public URI getURI();
}
