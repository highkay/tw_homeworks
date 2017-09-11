package com.thoughtworks.homeworks.ctm.solution;

import java.util.List;

public interface BinFactory {
	/**
	 * Create bins if there is no more space to put the item.
	 * 
	 * @return new created bins.
	 */
	List<Bin> createMoreBin();

	/**
	 * Get the all bins.
	 * 
	 * @return
	 */
	List<Bin> getBins();
}
