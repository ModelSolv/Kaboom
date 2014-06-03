package com.modelsolv.kaboom.model.rdm.nativeImpl;

import com.modelsolv.kaboom.model.rdm.RDMFactory;
import com.modelsolv.kaboom.model.rdm.RDMPrimitiveProperty;
import com.modelsolv.kaboom.model.rdm.ReferenceEmbed;
import com.modelsolv.kaboom.model.rdm.ReferenceLink;
import com.modelsolv.kaboom.model.rdm.Resource;
import com.modelsolv.kaboom.model.rdm.ResourceDataModel;
import com.modelsolv.kaboom.object.CanonicalObjectReader;

/**
 * Factory class to create a resource data model in the native object format.
 * 
 * @author Ted
 * 
 */
public class NativeRDMFactory implements RDMFactory {

	@Override
	public RDMPrimitiveProperty createRDMPrimitiveProperty() {
		return new RDMPrimitivePropertyImpl();
	}

	@Override
	public RDMPrimitiveProperty createRDMPrimitiveProperty(String name) {
		return new RDMPrimitivePropertyImpl(name);
	}

	@Override
	public ReferenceLink createReferenceLink() {
		return new ReferenceLinkImpl();
	}

	@Override
	public ReferenceLink createReferenceLink(String name) {
		return new ReferenceLinkImpl(name);
	}

	@Override
	public ReferenceEmbed createReferenceEmbed() {
		return new ReferenceEmbedImpl();
	}

	@Override
	public ReferenceEmbed createReferenceEmbed(String name) {
		return new ReferenceEmbedImpl(name);
	}

	@Override
	public Resource createResource() {
		return new ResourceImpl();
	}

	@Override
	public ResourceDataModel createResourceDataModel() {
		return new ResourceDataModelImpl();
	}

}
