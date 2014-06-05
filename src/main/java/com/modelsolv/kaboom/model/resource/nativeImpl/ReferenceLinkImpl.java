package com.modelsolv.kaboom.model.resource.nativeImpl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.modelsolv.kaboom.model.canonical.CDMProperty;
import com.modelsolv.kaboom.model.canonical.CDMReferenceProperty;
import com.modelsolv.kaboom.model.resource.ObjectResourceDefinition;
import com.modelsolv.kaboom.model.resource.RDMProperty;
import com.modelsolv.kaboom.model.resource.ReferenceLink;
import com.modelsolv.kaboom.model.resource.ObjectResource;
import com.modelsolv.kaboom.model.resource.ResourceDefinition;
import com.modelsolv.kaboom.object.CanonicalObjectReader;

public class ReferenceLinkImpl extends RDMReferencePropertyImpl implements ReferenceLink {
	
	private LinkedHashMap<String, RDMProperty> includedProperties;
	
	private String linkRelation;
	
	private ResourceDefinition targetResource;
	
	public ReferenceLinkImpl(CDMReferenceProperty property) {
		super(property);
	}
	
	@Override
	public Iterable<RDMProperty> getIncludedProperties() {
		if (includedProperties == null) {
			includedProperties = new LinkedHashMap<String, RDMProperty>();
		}
		return includedProperties.values();
	}

	@Override
	public String getLinkRelation() {
		return linkRelation;
	}

	@Override
	public ReferenceLink includingProperties(CDMProperty... properties) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReferenceLink includingProperties(String... properties) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLinkRelation(String linkRelation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ReferenceLink withLinkRelation(String linkRelation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectResourceDefinition getTargetResourceDefinition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTargetResourceDefinition(ObjectResourceDefinition resourceDef) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ReferenceLink withTargetResourceDefinition(
			ObjectResourceDefinition resourceDef) {
		// TODO Auto-generated method stub
		return null;
	}

}
