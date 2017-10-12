package com.nagarro.amcart.platform.converters;

import com.nagarro.amcart.platform.exceptions.ConversionException;

/**
 * Interface for a populator. A populator sets values in a target instance based
 * on values in the source instance.
 *
 * @param <SOURCE>
 *            the type of the source object
 * @param <TARGET>
 *            the type of the destination object
 */
public interface Populator<SOURCE, TARGET> {
	/**
	 * Populate the target instance with values from the source instance.
	 *
	 * @param source
	 *            the source object
	 * @param target
	 *            the target to fill
	 * @throws ConversionException
	 *             if an error occurs
	 */
	void populate(SOURCE source, TARGET target) throws ConversionException;
}
