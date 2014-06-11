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
import com.modelsolv.kaboom.model.canonical.CanonicalDataType;
import com.modelsolv.kaboom.model.resource.RDMReferenceProperty;

public class RDMReferencePropertyImpl extends RDMPropertyImpl implements
		RDMReferenceProperty {

	public RDMReferencePropertyImpl(CDMReferenceProperty cdmProperty) {
		super(cdmProperty);
	}

	@Override
	public CanonicalDataType getTargetDataType() {
		return getCDMProperty().getTargetDataType();
	}

	@Override
	public CDMReferenceProperty getCDMProperty() {
		return (CDMReferenceProperty) super.getCDMProperty();
	}

	@Override
	public CDMReferenceProperty getInverseProperty() {
		return getCDMProperty().getInverseProperty();
	}

}
