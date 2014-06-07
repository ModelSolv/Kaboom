package com.modelsolv.kaboom.model.resource;

import com.modelsolv.kaboom.model.canonical.CDMPrimitiveProperty;
import com.modelsolv.kaboom.model.canonical.ReadableCDMPrimitiveProperty;

public interface RDMPrimitiveProperty extends RDMProperty, ReadableCDMPrimitiveProperty {
	@Override 
	public CDMPrimitiveProperty getCDMProperty();
}
