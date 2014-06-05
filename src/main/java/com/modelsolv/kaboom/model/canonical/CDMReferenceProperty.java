package com.modelsolv.kaboom.model.canonical;

public interface CDMReferenceProperty extends CDMProperty, ReadableCDMReferenceProperty {
	
	public void setTargetDataType(CanonicalDataType type);

}
