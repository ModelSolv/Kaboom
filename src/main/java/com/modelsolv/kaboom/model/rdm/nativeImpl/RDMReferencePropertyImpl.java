package com.modelsolv.kaboom.model.rdm.nativeImpl;

import com.modelsolv.kaboom.model.rdm.RDMReferenceProperty;
import com.modelsolv.kaboom.model.rdm.Resource;
import com.modelsolv.kaboom.object.CanonicalObjectReader;

public class RDMReferencePropertyImpl extends RDMPropertyImpl implements
		RDMReferenceProperty {

	private Object targetObject;

	private Resource targetResource;

	public RDMReferencePropertyImpl() {
		super();
	}

	public RDMReferencePropertyImpl(String propName) {
		super(propName);
	}

	@Override
	public Object getTargetObject() {
		return targetObject;
	}

	public Resource getTargetResource() {
		return targetResource;
	}

}
