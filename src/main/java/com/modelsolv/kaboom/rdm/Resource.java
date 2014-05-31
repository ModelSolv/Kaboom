package com.modelsolv.kaboom.rdm;

import java.net.URI;

public interface Resource {
	
	public CanonicalObject getBoundObject();

	public ResourceDataModel getDataModel();
	
	public URI getURI();
}
