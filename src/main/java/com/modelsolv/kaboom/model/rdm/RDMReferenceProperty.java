package com.modelsolv.kaboom.model.rdm;

import com.modelsolv.kaboom.object.CanonicalObject;

public interface RDMReferenceProperty extends RDMProperty {
	
	public CanonicalObject getTargetObject();
	
}
