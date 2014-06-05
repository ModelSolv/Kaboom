package com.modelsolv.kaboom.model.resource.nativeImpl;

import com.modelsolv.kaboom.model.canonical.CDMReferenceProperty;
import com.modelsolv.kaboom.model.resource.ObjectResourceDefinition;
import com.modelsolv.kaboom.model.resource.ReferenceEmbed;
import com.modelsolv.kaboom.model.resource.ResourceDataModel;
import com.modelsolv.kaboom.object.CanonicalObjectReader;

public class ReferenceEmbedImpl extends RDMReferencePropertyImpl implements ReferenceEmbed {

	private ResourceDataModel targetRDM;
	
	public ReferenceEmbedImpl(CDMReferenceProperty cdmProperty) {
		super(cdmProperty);
	}

	@Override
	public ResourceDataModel getEmbeddedModel() {
		if(targetRDM == null) {
			return new ResourceDataModelImpl();
		}
		return targetRDM;
	}

	@Override
	public ReferenceEmbed withTargetResourceDefinition(
			ObjectResourceDefinition resourceDef) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
