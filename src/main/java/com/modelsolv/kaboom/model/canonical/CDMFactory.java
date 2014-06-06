package com.modelsolv.kaboom.model.canonical;

import com.modelsolv.kaboom.model.canonical.nativeImpl.NativeCDMFactory;

public interface CDMFactory {
	
	public static CDMFactory INSTANCE = new NativeCDMFactory();
	
	public CanonicalDataType createDataType(String name);
	
	public CDMPrimitiveProperty createPrimitiveProperty(String name, PrimitiveDataType type);
	
	public CDMPrimitiveProperty createPrimitiveProperty(String name, PrimitiveDataType type, Cardinality cardinality);
	
	public CDMReferenceProperty createReferenceProperty(String name, CanonicalDataType targetType);
	
	public CDMReferenceProperty createReferenceProperty(String name, CanonicalDataType targetType, Cardinality cardinality);
}
