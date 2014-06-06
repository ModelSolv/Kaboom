package com.modelsolv.kaboom.model.resource.nativeImpl;

import com.modelsolv.kaboom.model.canonical.CDMProperty;
import com.modelsolv.kaboom.model.canonical.CDMReferenceProperty;
import com.modelsolv.kaboom.model.resource.ObjectResourceDefinition;
import com.modelsolv.kaboom.model.resource.RDMProperty;
import com.modelsolv.kaboom.model.resource.ReferenceLink;

public class ReferenceLinkImpl extends RDMReferencePropertyImpl implements
		ReferenceLink {

	private RDMPropertyCollection includedProperties = new RDMPropertyCollection();

	private String linkRelation;

	private ObjectResourceDefinition targetResource;

	public ReferenceLinkImpl(CDMReferenceProperty property) {
		super(property);
	}

	@Override
	public Iterable<RDMProperty> getIncludedProperties() {
		return includedProperties;
	}

	@Override
	public String getLinkRelation() {
		return linkRelation;
	}

	@Override
	public ReferenceLink includingProperties(CDMProperty... properties) {
		includedProperties.withProperties(properties);
		return this;
	}

	@Override
	public ReferenceLink includingProperties(String... properties) {
		includedProperties.withProperties(getTargetDataType(), properties);
		return null;
	}

	@Override
	public void setLinkRelation(String linkRelation) {
		this.linkRelation = linkRelation;
	}

	@Override
	public ReferenceLink withLinkRelation(String linkRelation) {
		setLinkRelation(linkRelation);
		return this;
	}

	@Override
	public ObjectResourceDefinition getTargetResourceDefinition() {
		return this.targetResource;
	}

	@Override
	public void setTargetResourceDefinition(ObjectResourceDefinition resourceDef) {
		this.targetResource = resourceDef;
	}

	@Override
	public ReferenceLink withTargetResourceDefinition(
			ObjectResourceDefinition resourceDef) {
		setTargetResourceDefinition(resourceDef);
		return this;
	}

}
