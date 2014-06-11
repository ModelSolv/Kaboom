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
import com.modelsolv.kaboom.model.canonical.CDMReferenceProperty;
import com.modelsolv.kaboom.model.canonical.CDMFactory;
import com.modelsolv.kaboom.model.canonical.CanonicalDataType;
import com.modelsolv.kaboom.model.canonical.Cardinality;
import com.modelsolv.kaboom.model.canonical.PrimitiveDataType;

public class NativeCDMFactory implements CDMFactory {

	@Override
	public CanonicalDataType createDataType(String name) {
		return new CanonicalDataTypeImpl(name);
	}

	@Override
	public CDMPrimitiveProperty createPrimitiveProperty(String name,
			PrimitiveDataType type) {
		return new CDMPrimitivePropertyImpl(name, type);
	}

	@Override
	public CDMPrimitiveProperty createPrimitiveProperty(String name,
			PrimitiveDataType type, Cardinality cardinality) {
		return new CDMPrimitivePropertyImpl(name, type, cardinality);
	}

	@Override
	public CDMReferenceProperty createReferenceProperty(String name,
			CanonicalDataType targetType) {
		return new CDMReferencePropertyImpl(name, targetType);
	}

	@Override
	public CDMReferenceProperty createReferenceProperty(String name,
			CanonicalDataType targetType, Cardinality cardinality) {
		return new CDMReferencePropertyImpl(name, targetType, cardinality);
	}
}
