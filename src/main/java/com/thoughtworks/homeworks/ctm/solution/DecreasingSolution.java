package com.thoughtworks.homeworks.ctm.solution;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class DecreasingSolution implements Solution {
	public void decreasingItems(List<Item> items) {
		Collections.sort(items, new Comparator<Item>() {

			public int compare(Item item1, Item item2) {
				
				return item2.getSpace() - item1.getSpace();
			}

		});
	}
}
