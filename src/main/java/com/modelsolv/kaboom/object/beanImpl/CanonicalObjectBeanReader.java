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
package com.modelsolv.kaboom.object.beanImpl;

import org.apache.commons.beanutils.PropertyUtils;

import com.modelsolv.kaboom.model.canonical.CDMProperty;
import com.modelsolv.kaboom.model.resource.RDMProperty;
import com.modelsolv.kaboom.object.CanonicalObjectReader;

/**
 * A wrapper around a Java Bean that exposes the CanonicalObject interface.
 * Accesses properties reflectively, and adapts references recursively.
 * 
 * @author Ted
 * 
 */
public class CanonicalObjectBeanReader implements CanonicalObjectReader {

	@Override
	public Object getPropertyValue(Object obj, RDMProperty prop) {
		return getPropertyValue(obj, prop.getName());
	}

	@Override
	public Object getPropertyValue(Object obj, CDMProperty prop) {
		return getPropertyValue(obj, prop.getName());
	}

	@Override
	public Object getPropertyValue(Object obj, String propName) {
		try {
			return PropertyUtils.getProperty(obj, propName);
		} catch (Exception e) {
			throw new RuntimeException("Could not retrieve value for property " + propName, e);
		}
	}
}
