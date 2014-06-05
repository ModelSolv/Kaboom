package com.modelsolv.kaboom.model.canonical.nativeImpl;

import com.modelsolv.kaboom.model.canonical.CDMReferenceProperty;
import com.modelsolv.kaboom.model.canonical.CanonicalDataType;
import com.modelsolv.kaboom.model.canonical.Cardinality;

public class CDMReferencePropertyImpl extends CDMPropertyImpl implements
		CDMReferenceProperty {

	private CanonicalDataType type;
	
	public CDMReferencePropertyImpl(String name, CanonicalDataType type) {
		super(name);
		setTargetDataType(type);
	}

	public CDMReferencePropertyImpl(String name, CanonicalDataType type, Cardinality cardinality) {
		super(name, cardinality);
		setTargetDataType(type);
	}

	@Override
	public CanonicalDataType getTargetDataType() {
		return type;
	}

	@Override
	public void setTargetDataType(CanonicalDataType type) {
		this.type = type;
	}

}
