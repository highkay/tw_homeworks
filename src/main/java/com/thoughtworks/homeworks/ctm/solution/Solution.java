package com.thoughtworks.homeworks.ctm.solution;

import java.util.List;

public interface Solution {
	/**
	 * 
	 * Pack the items into bins which is created by BinFactory.
	 * 
	 * @param allItems
	 * @param binFactory
	 */
	public void pack(List<Item> allItems, BinFactory binFactory);
}
