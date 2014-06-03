package com.modelsolv.kaboom.model.rdm.nativeImpl;

import com.modelsolv.kaboom.model.rdm.ReferenceEmbed;
import com.modelsolv.kaboom.model.rdm.ResourceDataModel;
import com.modelsolv.kaboom.object.CanonicalObjectReader;

public class ReferenceEmbedImpl extends RDMReferencePropertyImpl implements ReferenceEmbed {

	private ResourceDataModel targetRDM;
	
	public ReferenceEmbedImpl() {
		super();
	}

	public ReferenceEmbedImpl(String propName) {
		super(propName);
	}

	@Override
	public ResourceDataModel getEmbeddedModel() {
		return targetRDM;
	}

}
