package com.modelsolv.kaboom.rdm;

public interface ReferenceEmbed extends RDMReferenceProperty {
	
	/**
	 * Returns the resource data model for the embedded data type.
	 * @return
	 */
	public ResourceDataModel getEmbeddedModel();

}
