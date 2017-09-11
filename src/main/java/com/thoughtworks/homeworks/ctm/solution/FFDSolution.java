package com.thoughtworks.homeworks.ctm.solution;

import java.util.Iterator;
import java.util.List;

public class FFDSolution extends DecreasingSolution {

	private Bin findFirstFitBin(Item item, List<Bin> bins) {
		for (Bin bin : bins) {
			// find the first fit bin and put in the item
			if (item.getSpace() < bin.getRemainSpace()) {
				return bin;
			}
		}
		return null;
	}

	public void pack(List<Item> allItems, BinFactory binFactory) {

		decreasingItems(allItems);
		Iterator<Item> items = allItems.iterator();
		while (items.hasNext()) {
			Item item = items.next();
			Bin firstFitBin = findFirstFitBin(item, binFactory.getBins());
			if (firstFitBin != null) {
				try {
					firstFitBin.addItem(item);
				} catch (Exception e) {
					e.printStackTrace();
					System.err.println(e.getMessage());
				}
			} else {
				try {
					findFirstFitBin(item, binFactory.createMoreBin()).addItem(item);
				} catch (Exception e) {

					e.printStackTrace();
					System.err.println(e.getMessage());
				}
			}
		}
	}

}
