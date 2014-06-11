/*******************************************************************************
 * Copyright (c) 2014 ModelSolv, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     ModelSolv, Inc. - initial API and implementation.
 *******************************************************************************/
package com.modelsolv.kaboom.model.resource.nativeImpl;

import java.net.URI;

import com.modelsolv.kaboom.model.canonical.CDMPrimitiveProperty;
import com.modelsolv.kaboom.model.canonical.CDMReferenceProperty;
import com.modelsolv.kaboom.model.canonical.CanonicalDataType;
import com.modelsolv.kaboom.model.resource.ObjectResource;
import com.modelsolv.kaboom.model.resource.ObjectResourceDefinition;
import com.modelsolv.kaboom.model.resource.RDMFactory;
import com.modelsolv.kaboom.model.resource.RDMPrimitiveProperty;
import com.modelsolv.kaboom.model.resource.ReferenceEmbed;
import com.modelsolv.kaboom.model.resource.ReferenceLink;
import com.modelsolv.kaboom.model.resource.ResourceDataModel;

/**
 * Factory class to create a resource data model in the native object format.
 * 
 * @author Ted
 * 
 */
public class NativeRDMFactory implements RDMFactory {

	@Override
	public RDMPrimitiveProperty createRDMPrimitiveProperty(
			CDMPrimitiveProperty cdmProperty) {
		return new RDMPrimitivePropertyImpl(cdmProperty);
	}

	@Override
	public ReferenceLink createReferenceLink(CDMReferenceProperty cdmProperty) {
		return new ReferenceLinkImpl(cdmProperty);
	}

	@Override
	public ReferenceEmbed createReferenceEmbed(CDMReferenceProperty cdmProperty) {
		return new ReferenceEmbedImpl(cdmProperty);
	}

	@Override
	public ResourceDataModel createResourceDataModel(CanonicalDataType cdt) {
		return new ResourceDataModelImpl(cdt);
	}

	@Override
	public ObjectResource createObjectResource(Object canonicalObject, URI uri,
			ObjectResourceDefinition definition) {
		return new ObjectResourceImpl(canonicalObject, uri, definition);
	}

	@Override
	public ObjectResourceDefinition createObjectResourceDefinition(
			String uriTemplate, ResourceDataModel rdm) {
		return new ObjectResourceDefinitionImpl(uriTemplate, rdm);
	}

}
