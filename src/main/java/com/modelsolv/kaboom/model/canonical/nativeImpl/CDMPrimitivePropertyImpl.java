package com.modelsolv.kaboom.model.canonical.nativeImpl;

import com.modelsolv.kaboom.model.canonical.CDMPrimitiveProperty;
import com.modelsolv.kaboom.model.canonical.Cardinality;
import com.modelsolv.kaboom.model.canonical.PrimitiveDataType;

public class CDMPrimitivePropertyImpl extends CDMPropertyImpl implements
		CDMPrimitiveProperty {

	private PrimitiveDataType type = PrimitiveDataType.STRING;

	public CDMPrimitivePropertyImpl(String name, PrimitiveDataType type) {
		super(name);
		this.type = type;
	}

	public CDMPrimitivePropertyImpl(String name, PrimitiveDataType type,
			Cardinality cardinality) {
		super(name, cardinality);
		this.type = type;
	}

	@Override
	public PrimitiveDataType getType() {
		return type;
	}

	@Override
	public void setType(PrimitiveDataType type) {
		this.type = type;
	}

}
