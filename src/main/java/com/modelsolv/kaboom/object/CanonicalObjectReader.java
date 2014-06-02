package com.modelsolv.kaboom.object;

import com.modelsolv.kaboom.model.rdm.RDMProperty;


public interface CanonicalObjectReader {
	
	public Object getPropertyValue(RDMProperty prop);
	
	public Object getPropertyValue(String propName);
}
