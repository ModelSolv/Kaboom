package com.modelsolv.kaboom.model.resource;

import com.modelsolv.kaboom.model.canonical.CDMReferenceProperty;
import com.modelsolv.kaboom.model.canonical.ReadableCDMReferenceProperty;

public interface RDMReferenceProperty extends RDMProperty, ReadableCDMReferenceProperty {
	
	@Override 
	public CDMReferenceProperty getCDMProperty();
		
}
