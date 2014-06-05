package com.modelsolv.kaboom.model.resource;

import com.modelsolv.kaboom.model.canonical.CDMPrimitiveProperty;
import com.modelsolv.kaboom.model.canonical.CDMReferenceProperty;
import com.modelsolv.kaboom.model.canonical.CanonicalDataType;

public interface RDMFactory {

	public RDMPrimitiveProperty createRDMPrimitiveProperty(
			CDMPrimitiveProperty cdmProperty);

	public ReferenceLink createReferenceLink(CDMReferenceProperty cdmProperty);

	public ReferenceEmbed createReferenceEmbed(CDMReferenceProperty cdmProperty);

	public ResourceDataModel createResourceDataModel(CanonicalDataType cdt);

}
