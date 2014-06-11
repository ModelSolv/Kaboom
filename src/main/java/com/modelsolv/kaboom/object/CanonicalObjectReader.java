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
package com.modelsolv.kaboom.object;

import com.modelsolv.kaboom.model.canonical.CDMProperty;
import com.modelsolv.kaboom.model.resource.RDMProperty;

public interface CanonicalObjectReader {
	
	public Object getPropertyValue(Object obj, CDMProperty prop);
	
	public Object getPropertyValue(Object obj, RDMProperty prop);
	
	public Object getPropertyValue(Object obj, String propName);
}
