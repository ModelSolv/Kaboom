package com.modelsolv.kaboom.model.rdm.nativeImpl;

import java.util.ArrayList;
import java.util.List;

import com.modelsolv.kaboom.model.rdm.RDMProperty;
import com.modelsolv.kaboom.model.rdm.ResourceDataModel;

public class ResourceDataModelImpl implements ResourceDataModel {

	private List<RDMProperty> includedProperties;
	
	@Override
	public List<RDMProperty> getIncludedProperties() {
		if(includedProperties == null) {
			includedProperties = new ArrayList<RDMProperty>();
		}
		return includedProperties;
	}

}
