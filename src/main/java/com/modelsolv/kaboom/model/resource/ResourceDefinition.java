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

/**
 * A definition of a class of resources. The class may be a singleton
 * (membership of one), or a template for multiple resources that share a
 * similar pattern.
 * 
 * @author Ted
 * 
 */
public interface ResourceDefinition {

	public String getName();

}
