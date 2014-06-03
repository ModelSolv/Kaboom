package com.modelsolv.kaboom.model.rdm.nativeImpl;

import java.util.ArrayList;
import java.util.List;

import com.modelsolv.kaboom.model.rdm.RDMProperty;
import com.modelsolv.kaboom.model.rdm.ReferenceLink;
import com.modelsolv.kaboom.model.rdm.Resource;
import com.modelsolv.kaboom.object.CanonicalObjectReader;

public class ReferenceLinkImpl extends RDMReferencePropertyImpl implements ReferenceLink {
	
	private List<RDMProperty> includedProperties;
	
	private String linkRelation;
	
	public ReferenceLinkImpl() {
		super();
	}
	
	public ReferenceLinkImpl(String name) {
		super(name);
	}
	
	@Override
	public List<RDMProperty> getIncludedProperties() {
		if (includedProperties == null) {
			includedProperties = new ArrayList<RDMProperty>();
		}
		return includedProperties;
	}

	@Override
	public String getLinkRelation() {
		return linkRelation;
	}

}
