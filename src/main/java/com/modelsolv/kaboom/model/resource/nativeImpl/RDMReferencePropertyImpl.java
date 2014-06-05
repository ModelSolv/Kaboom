package com.modelsolv.kaboom.model.resource.nativeImpl;

import com.modelsolv.kaboom.model.canonical.CDMReferenceProperty;
import com.modelsolv.kaboom.model.canonical.CanonicalDataType;
import com.modelsolv.kaboom.model.resource.RDMReferenceProperty;
import com.modelsolv.kaboom.model.resource.ObjectResource;
import com.modelsolv.kaboom.object.CanonicalObjectReader;

public class RDMReferencePropertyImpl extends RDMPropertyImpl implements
		RDMReferenceProperty {

	public RDMReferencePropertyImpl(CDMReferenceProperty cdmProperty) {
		super(cdmProperty);
	}

	@Override
	public CanonicalDataType getTargetDataType() {
		return getCDMProperty().getTargetDataType();
	}
	
	@Override
	public CDMReferenceProperty getCDMProperty() {
		return (CDMReferenceProperty) super.getCDMProperty();
	}

}
