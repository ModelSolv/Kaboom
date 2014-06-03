package com.modelsolv.kaboom.object;

import com.modelsolv.kaboom.model.rdm.RDMProperty;


public interface CanonicalObjectReader {
	
	public Object getPropertyValue(Object obj, RDMProperty prop);
	
	public Object getPropertyValue(Object obj, String propName);
}
