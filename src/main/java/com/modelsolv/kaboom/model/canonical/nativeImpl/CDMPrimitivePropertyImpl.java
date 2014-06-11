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

import com.modelsolv.kaboom.model.canonical.CDMPrimitiveProperty;
import com.modelsolv.kaboom.model.canonical.Cardinality;
import com.modelsolv.kaboom.model.canonical.PrimitiveDataType;

public class CDMPrimitivePropertyImpl extends CDMPropertyImpl implements
		CDMPrimitiveProperty {

	private PrimitiveDataType type = PrimitiveDataType.STRING;

	public CDMPrimitivePropertyImpl(String name, PrimitiveDataType type) {
		super(name);
		this.type = type;
	}

	public CDMPrimitivePropertyImpl(String name, PrimitiveDataType type,
			Cardinality cardinality) {
		super(name, cardinality);
		this.type = type;
	}

	@Override
	public PrimitiveDataType getType() {
		return type;
	}

	@Override
	public void setType(PrimitiveDataType type) {
		this.type = type;
	}

}
