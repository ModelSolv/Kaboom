package com.modelsolv.kaboom.model.resource;

import com.modelsolv.kaboom.model.canonical.CDMPrimitiveProperty;
import com.modelsolv.kaboom.model.canonical.CDMProperty;
import com.modelsolv.kaboom.model.canonical.CDMReferenceProperty;
import com.modelsolv.kaboom.model.canonical.CanonicalDataType;

public interface ResourceDataModel {

	public CanonicalDataType getCanonicalDataType();

	public void setCanonicalDataType(CanonicalDataType type);

	public ResourceDataModel withCanonicalDataType(CanonicalDataType type);

	public Iterable<RDMProperty> getIncludedProperties();

	// Add a list of CDM properties from the CanonicalDataType
	public ResourceDataModel includingProperties(CDMProperty... properties);

	public ResourceDataModel includingProperties(String... properties);

	// Add Primitive Property from CDM Property
	public RDMPrimitiveProperty addPrimitiveProperty(
			CDMPrimitiveProperty property);

	// Add Primitive Property directly
	public RDMPrimitiveProperty withPrimitiveProperty(
			RDMPrimitiveProperty property);

	// Add ReferenceLink from CDMReferenceProperty
	public ReferenceLink withReferenceLink(CDMReferenceProperty property);

	// Add ReferenceLink Directly
	public ReferenceLink withReferenceLink(ReferenceLink refLink);

	// Add ReferenceEmbed from CDMReferenceProperty
	public ReferenceEmbed withReferenceEmbed(CDMReferenceProperty property);

	// Add ReferenceEmbed Directly
	public ReferenceEmbed withReferenceEmbed(ReferenceEmbed refEmbed);
}
