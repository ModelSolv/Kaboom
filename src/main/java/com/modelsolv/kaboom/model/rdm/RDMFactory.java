package com.modelsolv.kaboom.model.rdm;

import com.modelsolv.kaboom.object.CanonicalObjectReader;

public interface RDMFactory {
	
	public RDMPrimitiveProperty createRDMPrimitiveProperty();
	
	public RDMPrimitiveProperty createRDMPrimitiveProperty(String name);
	
	public ReferenceLink createReferenceLink();
	
	public ReferenceLink createReferenceLink(String name);

	public ReferenceEmbed createReferenceEmbed();
	
	public ReferenceEmbed createReferenceEmbed(String name);

	public Resource createResource();
	
	public ResourceDataModel createResourceDataModel();

}
