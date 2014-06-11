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
package com.modelsolv.kaboom.model.resource;

import java.net.URI;

public interface ObjectResource {
	
	public Object getCanonicalObject();
	
	public void setCanonicalObject(Object obj);

	public URI getURI();
	
	public void setURI(URI uri);
	
	public ObjectResourceDefinition getResourceDefinition();
	
	public void setResourceDefinition(ObjectResourceDefinition definition);
	
}
