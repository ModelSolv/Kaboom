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
package com.modelsolv.kaboom.model.resource.nativeImpl;

import com.modelsolv.kaboom.model.canonical.CDMProperty;
import com.modelsolv.kaboom.model.canonical.Cardinality;
import com.modelsolv.kaboom.model.resource.RDMProperty;

public abstract class RDMPropertyImpl implements RDMProperty {

	private CDMProperty cdmProperty;

	protected RDMPropertyImpl(CDMProperty property) {
		setCDMProperty(property);
	}

	@Override
	public Cardinality getCardinality() {
		return getCDMProperty().getCardinality();
	}

	@Override
	public CDMProperty getCDMProperty() {
		return cdmProperty;
	}

	@Override
	public void setCDMProperty(CDMProperty property) {
		this.cdmProperty = property;
	}

	@Override
	public String getName() {
		return getCDMProperty().getName();
	}

}
