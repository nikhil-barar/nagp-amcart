package com.nagarro.amcart.platform.converters.impl;

import org.apache.commons.lang.NotImplementedException;

import com.nagarro.amcart.platform.converters.Converter;
import com.nagarro.amcart.platform.converters.Populator;
import com.nagarro.amcart.platform.exceptions.ConversionException;

/**
 * Abstract implementation of the Converter interface. Implementations of this
 * base type can be used as a converter.
 */
public abstract class AbstractConverter<SOURCE, TARGET>
		implements Converter<SOURCE, TARGET>, Populator<SOURCE, TARGET> {
	private Class<TARGET> targetClass;

	@Override
	public TARGET convert(final SOURCE source) throws ConversionException {
		final TARGET target = targetClass == null ? throwException() : createFromClass();
		populate(source, target);
		return target;
	}

	/**
	 * Override this method to populate the target from the source
	 * 
	 * @param source
	 *            the source instance
	 * @param target
	 *            the target instance to fill
	 * 
	 * @see #setTargetClass(Class)
	 */
	@Override
	public abstract void populate(final SOURCE source, final TARGET target);

	/**
	 * Allows to specify the target object class directly.
	 */
	public void setTargetClass(final Class<TARGET> targetClass) {
		this.targetClass = targetClass;

		if (targetClass != null) {
			createFromClass();
		}
	}

	protected TARGET createFromClass() {
		try {
			return targetClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	protected TARGET throwException() {
		throw new NotImplementedException();
	}
}
