package com.modelsolv.kaboom.model.canonical;

import java.util.List;

public interface CanonicalDataType {
	
	public String getName();
	
	public void setName(String name);
	
	public Iterable<CDMProperty> getProperties();
	
	public CDMProperty getProperty(String propName);
	
	public boolean hasProperty(CDMProperty property);
	
	public CanonicalDataType withPrimitive(String name, PrimitiveDataType type);
	
	public CanonicalDataType withPrimitive(String name, PrimitiveDataType type, Cardinality cardinality);

	public CanonicalDataType withReference(String name, CanonicalDataType type);

	public CanonicalDataType withReference(String name, CanonicalDataType type, Cardinality cardinality);

}
