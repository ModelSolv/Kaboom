package com.modelsolv.kaboom.model.resource;

import com.modelsolv.kaboom.model.canonical.CDMProperty;
import com.modelsolv.kaboom.model.canonical.ReadableCDMProperty;

public interface RDMProperty extends ReadableCDMProperty {
	
	public CDMProperty getCDMProperty();
	
	public void setCDMProperty(CDMProperty property);
	
}
