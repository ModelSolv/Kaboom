package com.modelsolv.kaboom.model.canonical.nativeImpl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.modelsolv.kaboom.model.canonical.CDMProperty;
import com.modelsolv.kaboom.model.canonical.CanonicalDataType;
import com.modelsolv.kaboom.model.canonical.Cardinality;
import com.modelsolv.kaboom.model.canonical.PrimitiveDataType;

public class CanonicalDataTypeImpl implements CanonicalDataType {

	private String name;
	private LinkedHashMap<String, CDMProperty> properties = new LinkedHashMap<String, CDMProperty>();

	public CanonicalDataTypeImpl(String name) {
		this.name = name;
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
	public Iterable<CDMProperty> getProperties() {
		return properties.values();
	}

	@Override
	public CDMProperty getProperty(String propName) {
		if (properties.containsKey(propName)) {
			return properties.get(propName);
		}
		return null;
	}

	@Override
	public boolean hasProperty(CDMProperty property) {
		return properties.containsKey(property);
	}

	@Override
	public CanonicalDataType withPrimitive(String name, PrimitiveDataType type) {
		return withPrimitive(name, type, Cardinality.ONE);
	}

	@Override
	public CanonicalDataType withPrimitive(String name, PrimitiveDataType type,
			Cardinality cardinality) {
		add(new CDMPrimitivePropertyImpl(name, type, cardinality));
		return this;
	}

	@Override
	public CanonicalDataType withReference(String name, CanonicalDataType type) {
		return withReference(name, type, Cardinality.ONE);
	}

	@Override
	public CanonicalDataType withReference(String name, CanonicalDataType type,
			Cardinality cardinality) {
		add(new CDMReferencePropertyImpl(name, type, cardinality));
		return this;
	}

	private void add(CDMProperty property) {
		if (properties.containsKey(property.getName())) {
			throw new IllegalArgumentException(
					"A property with this name already exists.");
		}
		if (properties.containsValue(property)) {
			throw new IllegalArgumentException(
					"The property has already been added to the collection.");
		}
		properties.put(property.getName(), property);
	}
}
