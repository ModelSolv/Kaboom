package com.modelsolv.kaboom.model.resource.nativeImpl;

import com.modelsolv.kaboom.model.canonical.CDMPrimitiveProperty;
import com.modelsolv.kaboom.model.canonical.PrimitiveDataType;
import com.modelsolv.kaboom.model.resource.RDMPrimitiveProperty;

public class RDMPrimitivePropertyImpl extends RDMPropertyImpl implements
		RDMPrimitiveProperty {
	
	public RDMPrimitivePropertyImpl(CDMPrimitiveProperty cdmProperty) {
		super(cdmProperty);
	}

	@Override
	public PrimitiveDataType getType() {
		return ((CDMPrimitiveProperty) getCDMProperty()).getType();
	}

}
