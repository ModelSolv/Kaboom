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
package com.modelsolv.kaboom.model.canonical;

import com.modelsolv.kaboom.model.canonical.nativeImpl.NativeCDMFactory;

public interface CDMFactory {
	
	public static CDMFactory INSTANCE = new NativeCDMFactory();
	
	public CanonicalDataType createDataType(String name);
	
	public CDMPrimitiveProperty createPrimitiveProperty(String name, PrimitiveDataType type);
	
	public CDMPrimitiveProperty createPrimitiveProperty(String name, PrimitiveDataType type, Cardinality cardinality);
	
	public CDMReferenceProperty createReferenceProperty(String name, CanonicalDataType targetType);
	
	public CDMReferenceProperty createReferenceProperty(String name, CanonicalDataType targetType, Cardinality cardinality);
}
