package com.modelsolv.kaboom.model.rdm;

import java.net.URI;

import com.modelsolv.kaboom.object.CanonicalObjectReader;

public interface Resource {
	
	public CanonicalObjectReader getBoundObject();

	public ResourceDataModel getDataModel();
	
	public URI getURI();
}
