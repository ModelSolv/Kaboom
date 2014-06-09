package com.modelsolv.kaboom.model.resource.nativeImpl;

import com.modelsolv.kaboom.model.canonical.CDMReferenceProperty;
import com.modelsolv.kaboom.model.canonical.CanonicalDataType;
import com.modelsolv.kaboom.model.resource.RDMReferenceProperty;

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

	@Override
	public CDMReferenceProperty getInverseProperty() {
		return getCDMProperty().getInverseProperty();
	}

}
