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

public interface CDMReferenceProperty extends CDMProperty, ReadableCDMReferenceProperty {
	
	public void setTargetDataType(CanonicalDataType type);
	
	public CDMReferenceProperty withTargetDataType(CanonicalDataType type);
	
	public void setInverseProperty(CDMReferenceProperty inverse);
	
	public CDMReferenceProperty withInverseProperty(CDMReferenceProperty inverse);

}
