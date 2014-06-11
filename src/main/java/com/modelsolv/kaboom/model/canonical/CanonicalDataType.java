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


public interface CanonicalDataType {
	
	public String getName();
	
	public void setName(String name);
	
	public Iterable<CDMProperty> getProperties();
	
	public CDMProperty getProperty(String propName);
	
	public boolean hasProperty(CDMProperty property);
	
	public CanonicalDataType withPrimitive(String name, PrimitiveDataType type);
	
	public CanonicalDataType withPrimitive(String name, PrimitiveDataType type, Cardinality cardinality);

	public CanonicalDataType withReference(String name, CanonicalDataType type);

	public CanonicalDataType withReference(String name, CanonicalDataType type, Cardinality cardinality);

}
