package com.thoughtworks.homeworks.ctm.model;

import java.util.LinkedList;
import java.util.List;

import com.thoughtworks.homeworks.ctm.solution.Bin;
import com.thoughtworks.homeworks.ctm.solution.BinAlreadyFullException;
import com.thoughtworks.homeworks.ctm.solution.Item;

public class Session implements Bin {
	private List<Talk> talks;
	private int totalDuration;
	private int usedDuration;

	public Session(int totalDuration) {
		talks = new LinkedList<Talk>();
		this.totalDuration = totalDuration;
	}

	public int getRemainSpace() {

		return totalDuration - usedDuration;
	}

	public boolean isFull() {

		// could not add anymore lightening talk
		return getRemainSpace() < 5;
	}

	public int getTotalSpace() {

		return this.totalDuration;
	}

	public int getUsedSpace() {

		return this.usedDuration;
	}

	public int addItem(Item item) throws BinAlreadyFullException {

		if (getRemainSpace() < item.getSpace()) {
			throw new BinAlreadyFullException(
					"The bin remain space is " + getRemainSpace() + ", and the item space is " + item.getSpace() + ".");
		}
		this.talks.add((Talk) item);
		this.usedDuration += item.getSpace();
		return getRemainSpace();
	}

	public List<Talk> getTalks() {
		return talks;
	}

	public int fitItem(Item item) {
		// TODO Auto-generated method stub
		return getRemainSpace() - item.getSpace();
	}

}
