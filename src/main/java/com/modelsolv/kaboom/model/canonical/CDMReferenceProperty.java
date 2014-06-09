package com.modelsolv.kaboom.model.canonical;

public interface CDMReferenceProperty extends CDMProperty, ReadableCDMReferenceProperty {
	
	public void setTargetDataType(CanonicalDataType type);
	
	public CDMReferenceProperty withTargetDataType(CanonicalDataType type);
	
	public void setInverseProperty(CDMReferenceProperty inverse);
	
	public CDMReferenceProperty withInverseProperty(CDMReferenceProperty inverse);

}
