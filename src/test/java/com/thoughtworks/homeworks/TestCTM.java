package com.thoughtworks.homeworks;

import org.junit.Test;

import com.thoughtworks.homeworks.ctm.ConferenceTrackManagement;

public class TestCTM {

	@Test
	public void testStandard() {
		ConferenceTrackManagement.main(new String[] { "-f=src/test/resources/talks.txt", "-s=ffd" });
	}

	@Test
	public void testShort() {
		ConferenceTrackManagement.main(new String[] { "-f=src/test/resources/talks2.txt", "-s=bfd" });
	}

	@Test
	public void testLong() {
		ConferenceTrackManagement.main(new String[] { "-f=src/test/resources/talks3.txt", "-s=ffd" });
	}
}
