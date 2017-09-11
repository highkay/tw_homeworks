package com.thoughtworks.homeworks.ctm.model;

import com.thoughtworks.homeworks.ctm.solution.Item;

public class Talk implements Item {
	private String name;
	private int duration;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getSpace() {
		
		return getDuration();
	}
}
