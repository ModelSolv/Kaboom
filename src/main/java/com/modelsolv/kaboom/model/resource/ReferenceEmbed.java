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

public interface ReferenceEmbed extends RDMReferenceProperty {

	/**
	 * Returns the resource data model for the embedded data type.
	 * 
	 * @return
	 */
	public ResourceDataModel getEmbeddedModel();

	public void setEmbeddedDataModel(ResourceDataModel rdm);
	
	public ReferenceEmbed withEmbeddedDataModel(ResourceDataModel rdm);
}
