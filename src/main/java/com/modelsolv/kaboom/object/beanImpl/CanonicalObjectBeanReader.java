package com.modelsolv.kaboom.object.beanImpl;

import com.modelsolv.kaboom.model.rdm.RDMProperty;
import com.modelsolv.kaboom.object.CanonicalObjectReader;

/**
 * A wrapper around a Java Bean that exposes the CanonicalObject interface.
 * Accesses properties reflectively, and adapts references recursively.
 * 
 * @author Ted
 * 
 */
public class CanonicalObjectBeanReader implements CanonicalObjectReader {

	private Object bean;

	public CanonicalObjectBeanReader(Object pojo) {
		this.bean = pojo;
	}

	@Override
	public Object getPropertyValue(RDMProperty prop) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPropertyValue(String propName) {
		// TODO Auto-generated method stub
		return null;
	}

}
