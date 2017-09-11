package com.thoughtworks.homeworks.ctm.model;

public class Track {

	public static final int MORNING_SESSION_MAX_DURATION = 180;
	public static final int AFTERNOON_SESSION_MAX_DURATION = 240;

	private Session morningSession;
	private Session afternoonSession;

	public Session getMorningSession() {
		return morningSession;
	}

	public void setMorningSession(Session morningSession) {
		this.morningSession = morningSession;
	}

	public Session getAfternoonSession() {
		return afternoonSession;
	}

	public void setAfternoonSession(Session afternoonSession) {
		this.afternoonSession = afternoonSession;
	}

}
