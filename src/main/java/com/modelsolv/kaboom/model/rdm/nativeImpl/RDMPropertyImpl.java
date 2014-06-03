package com.modelsolv.kaboom.model.rdm.nativeImpl;

import com.modelsolv.kaboom.model.rdm.RDMProperty;

public abstract class RDMPropertyImpl implements RDMProperty {
	
	private String propName;
	
	protected RDMPropertyImpl() {};
	
	protected RDMPropertyImpl(String propName) {
		setName(propName);
	}


	@Override
	public String getName() {
		return propName;
	}
	
	public void setName(String propName) {
		this.propName = propName;
	}

}
