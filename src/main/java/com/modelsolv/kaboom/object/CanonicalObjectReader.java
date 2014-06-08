package com.modelsolv.kaboom.object;

import com.modelsolv.kaboom.model.canonical.CDMProperty;
import com.modelsolv.kaboom.model.resource.RDMProperty;

public interface CanonicalObjectReader {
	
	public Object getPropertyValue(Object obj, CDMProperty prop);
	
	public Object getPropertyValue(Object obj, RDMProperty prop);
	
	public Object getPropertyValue(Object obj, String propName);
}
