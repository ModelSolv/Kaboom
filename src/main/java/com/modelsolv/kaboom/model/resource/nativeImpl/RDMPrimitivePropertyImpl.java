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

import com.modelsolv.kaboom.model.canonical.CDMPrimitiveProperty;
import com.modelsolv.kaboom.model.canonical.PrimitiveDataType;
import com.modelsolv.kaboom.model.resource.RDMPrimitiveProperty;

public class RDMPrimitivePropertyImpl extends RDMPropertyImpl implements
		RDMPrimitiveProperty {
	
	public RDMPrimitivePropertyImpl(CDMPrimitiveProperty cdmProperty) {
		super(cdmProperty);
	}

	@Override
	public PrimitiveDataType getType() {
		return getCDMProperty().getType();
	}
	
	@Override
	public CDMPrimitiveProperty getCDMProperty() {
		return (CDMPrimitiveProperty) super.getCDMProperty();
	}

}
