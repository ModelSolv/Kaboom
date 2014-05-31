package com.modelsolv.kaboom.rdm;

public interface RDMFactory {
	
	public CanonicalObject createCanonicalObject();
	
	public RDMPrimitiveProperty createRDMPrimitiveProperty();
	
	public RDMPrimitiveProperty createRDMPrimitiveProperty(String name);
	
	public ReferenceLink createReferenceLink();
	
	public ReferenceEmbed createReferenceEmbed();
	
	public Resource createResource();
	
	public ResourceDataModel createResourceDataModel();

}
