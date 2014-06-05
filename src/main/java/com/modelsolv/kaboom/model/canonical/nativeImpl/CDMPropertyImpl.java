package com.modelsolv.kaboom.model.canonical.nativeImpl;

import com.modelsolv.kaboom.model.canonical.CDMProperty;
import com.modelsolv.kaboom.model.canonical.Cardinality;

public abstract class CDMPropertyImpl implements CDMProperty {

	private String name;
	private Cardinality cardinality = Cardinality.ONE;
	
	protected CDMPropertyImpl(String name) {
		setName(name);
	}

	protected CDMPropertyImpl(String name, Cardinality cardinality) {
		setName(name);
		setCardinality(cardinality);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Cardinality getCardinality() {
		return cardinality;
	}

	@Override
	public void setCardinality(Cardinality cardinality) {
		this.cardinality = cardinality;
	}

}
