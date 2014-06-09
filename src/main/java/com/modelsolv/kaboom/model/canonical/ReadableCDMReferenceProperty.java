package com.modelsolv.kaboom.model.canonical;

public interface ReadableCDMReferenceProperty extends ReadableCDMProperty {

	public CanonicalDataType getTargetDataType();
	
	public CDMReferenceProperty getInverseProperty();

}
