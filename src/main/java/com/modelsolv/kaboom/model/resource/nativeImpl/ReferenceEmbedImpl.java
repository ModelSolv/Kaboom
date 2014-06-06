package com.modelsolv.kaboom.model.resource.nativeImpl;

import com.modelsolv.kaboom.model.canonical.CDMReferenceProperty;
import com.modelsolv.kaboom.model.resource.ReferenceEmbed;
import com.modelsolv.kaboom.model.resource.ResourceDataModel;

public class ReferenceEmbedImpl extends RDMReferencePropertyImpl implements
		ReferenceEmbed {

	private ResourceDataModel targetRDM;

	public ReferenceEmbedImpl(CDMReferenceProperty cdmProperty) {
		super(cdmProperty);
	}

	@Override
	public ResourceDataModel getEmbeddedModel() {
		if (targetRDM == null) {
			return new ResourceDataModelImpl(this.getTargetDataType());
		}
		return targetRDM;
	}

	@Override
	public void setEmbeddedDataModel(ResourceDataModel rdm) {
		this.targetRDM = rdm;
	}

	@Override
	public ReferenceEmbed withEmbeddedDataModel(ResourceDataModel rdm) {
		setEmbeddedDataModel(rdm);
		return this;
	}

}
