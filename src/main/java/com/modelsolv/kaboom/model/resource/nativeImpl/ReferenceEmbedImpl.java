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

import com.modelsolv.kaboom.model.canonical.CDMReferenceProperty;
import com.modelsolv.kaboom.model.resource.ReferenceEmbed;
import com.modelsolv.kaboom.model.resource.ResourceDataModel;

public class ReferenceEmbedImpl extends RDMReferencePropertyImpl implements
		ReferenceEmbed {

	private ResourceDataModel targetRDM;

	public ReferenceEmbedImpl(CDMReferenceProperty cdmProperty) {
		super(cdmProperty);
	}

	@Override
	public ResourceDataModel getEmbeddedModel() {
		if (targetRDM == null) {
			return new ResourceDataModelImpl(this.getTargetDataType());
		}
		return targetRDM;
	}

	@Override
	public void setEmbeddedDataModel(ResourceDataModel rdm) {
		this.targetRDM = rdm;
	}

	@Override
	public ReferenceEmbed withEmbeddedDataModel(ResourceDataModel rdm) {
		setEmbeddedDataModel(rdm);
		return this;
	}

}
