package com.modelsolv.kaboom.model.rdm;

import com.modelsolv.kaboom.object.CanonicalObjectReader;

public interface RDMFactory {
	
	public CanonicalObjectReader createCanonicalObject();
	
	public RDMPrimitiveProperty createRDMPrimitiveProperty();
	
	public RDMPrimitiveProperty createRDMPrimitiveProperty(String name);
	
	public ReferenceLink createReferenceLink();
	
	public ReferenceEmbed createReferenceEmbed();
	
	public Resource createResource();
	
	public ResourceDataModel createResourceDataModel();

}
