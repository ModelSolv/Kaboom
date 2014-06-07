package com.modelsolv.kaboom.model.resource.nativeImpl;

import java.net.URI;

import com.modelsolv.kaboom.model.resource.ObjectResourceDefinition;
import com.modelsolv.kaboom.model.resource.ObjectResource;

public class ObjectResourceImpl implements ObjectResource {

	private Object canonicalObject;
	private ObjectResourceDefinition definition;
	private URI uri;
	
	public ObjectResourceImpl(Object canonicalObject, URI uri, ObjectResourceDefinition definition) {
		this.canonicalObject = canonicalObject;
		this.uri = uri;
		this.definition = definition;
	}

	@Override
	public Object getCanonicalObject() {
		return canonicalObject;
	}

	@Override
	public void setCanonicalObject(Object canonicalObject) {
		this.canonicalObject = canonicalObject;
	}

	@Override
	public ObjectResourceDefinition getResourceDefinition() {
		return definition;
	}

	@Override
	public void setResourceDefinition(ObjectResourceDefinition definition) {
		this.definition = definition;
	}

	@Override
	public URI getURI() {
		return uri;
	}

	@Override 
	public void setURI(URI uri) {
		this.uri = uri;
	}



}
