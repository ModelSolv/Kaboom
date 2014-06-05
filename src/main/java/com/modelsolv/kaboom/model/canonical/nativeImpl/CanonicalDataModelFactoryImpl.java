package com.modelsolv.kaboom.model.canonical.nativeImpl;

import com.modelsolv.kaboom.model.canonical.CDMPrimitiveProperty;
import com.modelsolv.kaboom.model.canonical.CDMReferenceProperty;
import com.modelsolv.kaboom.model.canonical.CanonicalDataModelFactory;
import com.modelsolv.kaboom.model.canonical.CanonicalDataType;
import com.modelsolv.kaboom.model.canonical.Cardinality;
import com.modelsolv.kaboom.model.canonical.PrimitiveDataType;

public class CanonicalDataModelFactoryImpl implements CanonicalDataModelFactory {

	@Override
	public CanonicalDataType createDataType(String name) {
		return new CanonicalDataTypeImpl(name);
	}

	@Override
	public CDMPrimitiveProperty createPrimitiveProperty(String name,
			PrimitiveDataType type) {
		return new CDMPrimitivePropertyImpl(name, type);
	}

	@Override
	public CDMPrimitiveProperty createPrimitiveProperty(String name,
			PrimitiveDataType type, Cardinality cardinality) {
		return new CDMPrimitivePropertyImpl(name, type, cardinality);
	}

	@Override
	public CDMReferenceProperty createReferenceProperty(String name,
			CanonicalDataType targetType) {
		return new CDMReferencePropertyImpl(name, targetType);
	}

	@Override
	public CDMReferenceProperty createReferenceProperty(String name,
			CanonicalDataType targetType, Cardinality cardinality) {
		return new CDMReferencePropertyImpl(name, targetType, cardinality);
	}
}
