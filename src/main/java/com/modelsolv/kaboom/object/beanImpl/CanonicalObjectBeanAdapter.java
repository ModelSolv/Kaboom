package com.modelsolv.kaboom.object.beanImpl;

import com.modelsolv.kaboom.model.rdm.RDMProperty;
import com.modelsolv.kaboom.object.CanonicalObject;

/**
 * A wrapper around a Java Bean that exposes the CanonicalObject interface.
 * Accesses properties reflectively, and adapts references recursively.
 * 
 * @author Ted
 * 
 */
public class CanonicalObjectBeanAdapter implements CanonicalObject {

	private Object bean;

	public CanonicalObjectBeanAdapter(Object pojo) {
		this.bean = pojo;
	}

	@Override
	public Object getPropertyValue(RDMProperty prop) {
		// TODO Auto-generated method stub
		return null;
	}

}
