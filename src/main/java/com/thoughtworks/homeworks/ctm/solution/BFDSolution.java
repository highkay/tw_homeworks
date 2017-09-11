package com.thoughtworks.homeworks.ctm.solution;

import java.util.Iterator;
import java.util.List;

public class BFDSolution extends DecreasingSolution {

	private Bin findBestFitBin(Item item, List<Bin> bins) {
		Bin bestFitBin = null;
		int bestFitSpace = 0;
		for (Bin bin : bins) {
			if (item.getSpace() < bin.getRemainSpace()) {
				// calculate the left space
				int fitSpace = bin.fitItem(item);
				if (bestFitSpace == 0 || fitSpace < bestFitSpace) {
					// reference the best fit bin
					bestFitSpace = fitSpace;
					bestFitBin = bin;
				}
			}
		}
		return bestFitBin;
	}

	public void pack(List<Item> allItems, BinFactory binFactory) {
		decreasingItems(allItems);
		Iterator<Item> items = allItems.iterator();
		while (items.hasNext()) {
			Item item = items.next();
			Bin bestFitBin = findBestFitBin(item, binFactory.getBins());
			if (bestFitBin != null) {
				// add item into the best fit bin
				try {
					bestFitBin.addItem(item);
				} catch (Exception e) {
					e.printStackTrace();
					System.err.println(e.getMessage());
				}
			} else {
				// if not find any fit bin, create more bins.
				try {
					findBestFitBin(item, binFactory.createMoreBin()).addItem(item);
				} catch (Exception e) {
					e.printStackTrace();
					System.err.println(e.getMessage());
				}
			}
		}
	}

}
