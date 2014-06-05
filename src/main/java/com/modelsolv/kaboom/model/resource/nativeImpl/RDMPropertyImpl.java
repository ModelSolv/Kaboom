package com.modelsolv.kaboom.model.resource.nativeImpl;

import com.modelsolv.kaboom.model.canonical.CDMProperty;
import com.modelsolv.kaboom.model.canonical.Cardinality;
import com.modelsolv.kaboom.model.resource.RDMProperty;

public abstract class RDMPropertyImpl implements RDMProperty {

	private CDMProperty cdmProperty;

	protected RDMPropertyImpl(CDMProperty property) {
		setCDMProperty(property);
	}

	@Override
	public Cardinality getCardinality() {
		return getCDMProperty().getCardinality();
	}

	@Override
	public CDMProperty getCDMProperty() {
		return cdmProperty;
	}

	@Override
	public void setCDMProperty(CDMProperty property) {
		this.cdmProperty = property;
	}

	@Override
	public String getName() {
		return getCDMProperty().getName();
	}

}
