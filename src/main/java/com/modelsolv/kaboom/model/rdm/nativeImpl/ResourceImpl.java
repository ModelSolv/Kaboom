package com.modelsolv.kaboom.model.rdm.nativeImpl;

import java.net.URI;

import com.modelsolv.kaboom.model.rdm.Resource;
import com.modelsolv.kaboom.model.rdm.ResourceDataModel;
import com.modelsolv.kaboom.object.CanonicalObjectReader;

public class ResourceImpl implements Resource {
	
	private Object canonicalObject;
	private ResourceDataModel rdm;
	private URI uri;

	@Override
	public Object getCanonicalObject() {
		return canonicalObject;
	}

	@Override
	public ResourceDataModel getResourceDataModel() {
		return rdm;
	}

	@Override
	public URI getURI() {
		return uri;
	}

}
