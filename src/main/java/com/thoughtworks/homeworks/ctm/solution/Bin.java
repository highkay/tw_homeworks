package com.thoughtworks.homeworks.ctm.solution;

public interface Bin {

	/**
	 * Get remain space of this bin.
	 * 
	 * @return
	 */
	public int getRemainSpace();

	/**
	 * Whether the bin is full.
	 * 
	 * @return
	 */
	public boolean isFull();

	/**
	 * Get total space of the bin.
	 * 
	 * @return
	 */
	public int getTotalSpace();

	/**
	 * Get used space by items in the bin.
	 * 
	 * @return
	 */
	public int getUsedSpace();

	/**
	 * Add a item into the bin.
	 * 
	 * @param item
	 * @return
	 * @throws Exception
	 */
	public int addItem(Item item) throws Exception;

	/**
	 * Test inputing the item for getting the left space.
	 * 
	 * @param item
	 * @return left space after putting of the bin.
	 */
	public int fitItem(Item item);

}
