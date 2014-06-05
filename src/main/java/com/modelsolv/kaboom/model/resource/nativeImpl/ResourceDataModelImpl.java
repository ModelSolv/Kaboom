package com.modelsolv.kaboom.model.resource.nativeImpl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.UnexpectedTypeException;

import com.modelsolv.kaboom.model.canonical.CDMPrimitiveProperty;
import com.modelsolv.kaboom.model.canonical.CDMProperty;
import com.modelsolv.kaboom.model.canonical.CDMReferenceProperty;
import com.modelsolv.kaboom.model.canonical.CanonicalDataType;
import com.modelsolv.kaboom.model.resource.ObjectResourceDefinition;
import com.modelsolv.kaboom.model.resource.ObjectResourceDefinitionRegistry;
import com.modelsolv.kaboom.model.resource.RDMPrimitiveProperty;
import com.modelsolv.kaboom.model.resource.RDMProperty;
import com.modelsolv.kaboom.model.resource.ReferenceEmbed;
import com.modelsolv.kaboom.model.resource.ReferenceLink;
import com.modelsolv.kaboom.model.resource.ResourceDataModel;

public class ResourceDataModelImpl implements ResourceDataModel {

	private CanonicalDataType canonicalType;

	private List<RDMProperty> includedProperties;

	private interface WrapStrategy {
		RDMProperty wrapCDMProperty(CDMProperty cdmProperty);
	}

	public ResourceDataModelImpl() {
	}

	public ResourceDataModelImpl(CanonicalDataType cdt) {
		setCanonicalDataType(cdt);
	}

	@Override
	public Iterable<RDMProperty> getIncludedProperties() {
		if (includedProperties == null) {
			includedProperties = new ArrayList<RDMProperty>();
		}
		// if we don't have a list of included properties, create one and return
		// it
		return null;
	}

	@Override
	public CanonicalDataType getCanonicalDataType() {
		return canonicalType;
	}

	@Override
	public void setCanonicalDataType(CanonicalDataType type) {
		canonicalType = type;
	}

	@Override
	public ResourceDataModel withCanonicalDataType(CanonicalDataType type) {
		setCanonicalDataType(type);
		return this;
	}

	@Override
	public ResourceDataModel includingProperties(CDMProperty... properties) {
		for (CDMProperty property : properties) {
			addCDMProperty(property);
		}
		return this;
	}

	@Override
	public ResourceDataModel includingProperties(String... properties) {
		for (String propName : properties) {
			CDMProperty property = getCanonicalDataType().getProperty(propName);
			addCDMProperty(property);
		}
		return this;
	}

	private void addCDMProperty(CDMProperty property) {
		if (property instanceof CDMPrimitiveProperty) {
			System.out.println(property);
			addPrimitiveProperty((CDMPrimitiveProperty) property);
		} else if (property instanceof CDMReferenceProperty) {
			CDMReferenceProperty refProp = (CDMReferenceProperty) property;
			CanonicalDataType targetType = refProp.getTargetDataType();
			ObjectResourceDefinition targetResourceDef = ObjectResourceDefinitionRegistry.INSTANCE
					.getResourceDefinition(targetType);
			if (targetResourceDef == null) {
				// there's no resource definition for this data type. Default to
				// embedded representation.
				addReferenceEmbed(refProp);
			} else {
				// if we have a resource definition, default to a reference
				// link.
				addReferenceLink(refProp);
			}
		} else {
			throw new UnexpectedTypeException("Unknown CDMProperty subtype.");
		}
	}

	private RDMProperty addCDMProperty(CDMProperty property, WrapStrategy wrapper) {
		RDMProperty rdmProp = wrapper.wrapCDMProperty(property);
		addRDMProperty(rdmProp);
		return rdmProp;
	}

	private void addRDMProperty(RDMProperty property) {
		if (!getCanonicalDataType().hasProperty(property.getCDMProperty())) {
			throw new IllegalArgumentException(
					"Only properties from the underlying CanonicalDataType may be included in the ResourceDataType.");
		}
		if (isCDMPropertyIncluded(property.getCDMProperty())) {
			throw new IllegalArgumentException(
					"The canonical property has already been added to the collection.");
		}
		if (includedProperties == null) {
			includedProperties = new ArrayList<RDMProperty>();
		}
		includedProperties.add(property);
	}

	@Override
	public RDMPrimitiveProperty withPrimitiveProperty(CDMPrimitiveProperty property) {
		return (RDMPrimitiveProperty) addCDMProperty(property, new WrapStrategy() {
			@Override
			public RDMProperty wrapCDMProperty(CDMProperty cdmProperty) {
				return new RDMPrimitivePropertyImpl(
						(CDMPrimitiveProperty) cdmProperty);
			}

		});
	}

	@Override
	public RDMPrimitiveProperty withPrimitiveProperty(
			RDMPrimitiveProperty property) {
		addRDMProperty(property);
		return property;
	}

	@Override
	public void addReferenceLink(CDMReferenceProperty property) {
		addCDMProperty(property, new WrapStrategy() {
			@Override
			public RDMProperty wrapCDMProperty(CDMProperty cdmProperty) {
				return new ReferenceLinkImpl((CDMReferenceProperty) cdmProperty);
			}

		});
	}

	@Override
	public ReferenceLink withReferenceLink(CDMReferenceProperty property) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addReferenceLink(ReferenceLink refLink) {
		// TODO Auto-generated method stub

	}

	@Override
	public ReferenceLink withReferenceLink(ReferenceLink refLink) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addReferenceEmbed(CDMReferenceProperty property) {
		addCDMProperty(property, new WrapStrategy() {
			@Override
			public RDMProperty wrapCDMProperty(CDMProperty cdmProperty) {
				return new ReferenceEmbedImpl(
						(CDMReferenceProperty) cdmProperty);
			}

		});
	}

	@Override
	public ReferenceEmbed withReferenceEmbed(CDMReferenceProperty property) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addReferenceEmbed(ReferenceEmbed refEmbed) {
		// TODO Auto-generated method stub

	}

	@Override
	public ReferenceEmbed withReferenceEmbed(ReferenceEmbed refEmbed) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Returns True iif the property is in the includedProperties list, which
	 * overrides the canonical type's properties list. Otherwise returns false.
	 * Note that this method is not intended to test whether the property will
	 * be included in the resource representation. If the includedProperties
	 * list is null, all properties will be included in the representation, but
	 * this method will still return false.
	 * 
	 * @param property
	 * @return
	 */
	private boolean isCDMPropertyIncluded(CDMProperty cdmProperty) {
		if (includedProperties == null) {
			return false;
		}
		for (RDMProperty rdmProperty : getIncludedProperties()) {
			if (rdmProperty.getCDMProperty() == cdmProperty) {
				return true;
			}
		}
		return false;
	}
}
