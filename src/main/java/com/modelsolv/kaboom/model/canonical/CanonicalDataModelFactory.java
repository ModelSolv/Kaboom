package com.modelsolv.kaboom.model.canonical;

public interface CanonicalDataModelFactory {
	
	public CanonicalDataType createDataType(String name);
	
	public CDMPrimitiveProperty createPrimitiveProperty(String name, PrimitiveDataType type);
	
	public CDMPrimitiveProperty createPrimitiveProperty(String name, PrimitiveDataType type, Cardinality cardinality);
	
	public CDMReferenceProperty createReferenceProperty(String name, CanonicalDataType targetType);
	
	public CDMReferenceProperty createReferenceProperty(String name, CanonicalDataType targetType, Cardinality cardinality);
}
