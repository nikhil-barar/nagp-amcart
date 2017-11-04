package com.nagarro.amcart.platform.converters;

import java.util.List;

/**
 * Interface for a list of populators.
 * 
 * @param <SOURCE>
 *            the type of the source object
 * @param <TARGET>
 *            the type of the destination object
 */
public interface PopulatorList<SOURCE, TARGET> {
	/**
	 * Get the list of populators.
	 * 
	 * @return the populators.
	 */
	List<Populator<SOURCE, TARGET>> getPopulators();

	/**
	 * Set the list of populators.
	 * 
	 * @param populators
	 *            the populators
	 */
	void setPopulators(List<Populator<SOURCE, TARGET>> populators);
}
