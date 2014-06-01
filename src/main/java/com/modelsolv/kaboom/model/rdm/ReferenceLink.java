package com.modelsolv.kaboom.model.rdm;

import java.util.List;

public interface ReferenceLink extends RDMReferenceProperty {

	/**
	 * The list of included "decorator" properties included in the
	 * ReferenceLink. These may be defined directly in the ReferenceLink, or in
	 * a named LinkDescriptor.
	 * <p>
	 * TODO : Right now these are modeled as property paths, allowing promotion
	 * of properties through arbitrary levels. We don't want to complicate the
	 * serializer with this, so for now we assume they are direct properties of
	 * the target data type. Implementation should exclude indirect properties
	 * for now.
	 * 
	 * @return
	 */
	public List<RDMProperty> getIncludedProperties();

	/**
	 * 
	 * @return the Link Relation if specified, otherwise null
	 */
	public String getLinkRelation();

	/**
	 * The target resource of the link. Will be the default resource for the
	 * referenced data type, unless it's overridden in the ReferenceLink
	 * definition.
	 * 
	 * @return
	 */
	public Resource getTargetResource();
}
