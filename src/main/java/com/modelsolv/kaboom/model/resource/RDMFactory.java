package com.modelsolv.kaboom.model.resource;

import java.net.URI;

import com.modelsolv.kaboom.model.canonical.CDMPrimitiveProperty;
import com.modelsolv.kaboom.model.canonical.CDMReferenceProperty;
import com.modelsolv.kaboom.model.canonical.CanonicalDataType;
import com.modelsolv.kaboom.model.resource.nativeImpl.NativeRDMFactory;

public interface RDMFactory {

	public static RDMFactory INSTANCE = new NativeRDMFactory();

	public RDMPrimitiveProperty createRDMPrimitiveProperty(
			CDMPrimitiveProperty cdmProperty);

	public ReferenceLink createReferenceLink(CDMReferenceProperty cdmProperty);

	public ReferenceEmbed createReferenceEmbed(CDMReferenceProperty cdmProperty);

	public ResourceDataModel createResourceDataModel(CanonicalDataType cdt);
	
	public ObjectResourceDefinition createObjectResourceDefinition(String uriTemplate, ResourceDataModel rdm);

	public ObjectResource createObjectResource(Object canonicalObject, URI uri,
			ObjectResourceDefinition ord);

}
