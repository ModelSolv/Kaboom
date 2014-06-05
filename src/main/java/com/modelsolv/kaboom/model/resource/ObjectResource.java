package com.modelsolv.kaboom.model.resource;

import java.net.URI;

import com.modelsolv.kaboom.object.CanonicalObjectReader;

public interface ObjectResource {
	
	public Object getCanonicalObject();
	
	public void setCanonicalObject(Object obj);

	public URI getURI();
	
	public void setURI(URI uri);
	
	public ObjectResourceDefinition getResourceDefinition();
	
	public void setResourceDefinition(ObjectResourceDefinition definition);
	
}
