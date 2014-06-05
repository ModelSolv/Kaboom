package com.modelsolv.kaboom.model.resource;

public interface ReferenceEmbed extends RDMReferenceProperty {

	/**
	 * Returns the resource data model for the embedded data type.
	 * 
	 * @return
	 */
	public ResourceDataModel getEmbeddedModel();

	public void setEmbeddedDataModel(ResourceDataModel rdm);
	
	public ReferenceEmbed withEmbeddedDataModel(ResourceDataModel rdm);
}
