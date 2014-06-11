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
package com.modelsolv.kaboom.model.canonical.nativeImpl;

import com.modelsolv.kaboom.model.canonical.CDMReferenceProperty;
import com.modelsolv.kaboom.model.canonical.CanonicalDataType;
import com.modelsolv.kaboom.model.canonical.Cardinality;

public class CDMReferencePropertyImpl extends CDMPropertyImpl implements
		CDMReferenceProperty {

	private CanonicalDataType type;
	private CDMReferenceProperty inverse;

	public CDMReferencePropertyImpl(String name, CanonicalDataType type) {
		super(name);
		setTargetDataType(type);
	}

	public CDMReferencePropertyImpl(String name, CanonicalDataType type,
			Cardinality cardinality) {
		super(name, cardinality);
		setTargetDataType(type);
	}

	@Override
	public CanonicalDataType getTargetDataType() {
		return type;
	}

	@Override
	public void setTargetDataType(CanonicalDataType type) {
		this.type = type;
	}

	@Override
	public CDMReferenceProperty withTargetDataType(CanonicalDataType type) {
		setTargetDataType(type);
		return this;
	}

	@Override
	public CDMReferenceProperty getInverseProperty() {
		return inverse;
	}

	@Override
	public void setInverseProperty(CDMReferenceProperty inverse) {
		this.inverse = inverse;
	}

	@Override
	public CDMReferenceProperty withInverseProperty(CDMReferenceProperty inverse) {
		setInverseProperty(inverse);
		return this;
	}
}
