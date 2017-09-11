package com.thoughtworks.homeworks.ctm;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import com.thoughtworks.homeworks.ctm.model.Session;
import com.thoughtworks.homeworks.ctm.model.Talk;
import com.thoughtworks.homeworks.ctm.model.Track;
import com.thoughtworks.homeworks.ctm.solution.Bin;
import com.thoughtworks.homeworks.ctm.solution.BinFactory;
import com.thoughtworks.homeworks.ctm.solution.Item;
import com.thoughtworks.homeworks.ctm.solution.Solution;

public class ConferenceTrackManagement {

	public static void printUsage() {
		System.out.println("Usage:");
		System.out.println("-f: must provide a file for input.");
		System.out.println("-s: may specific an algorithm. not nessesarily.");
		System.out.println("    (default)bfd, aka \"best fit decreasing\".");
		System.out.println("    ffd, aka \"first fit decreasing\".");
		System.out.println("    (not impl yet)bcp, aka \"branch-and-cut-and-price\".");
		System.out.println("Example 1:");
		System.out.println("-f=talks.txt");
		System.out.println("Example 2:");
		System.out.println("-f=talks.txt -s=ffd");
	}

	public static final String DEFAULT_SOLUTION = "bfd";

	public static void main(String[] args) {

		if (args.length == 0) {
			printUsage();
			System.exit(0);
		}
		// extract the params from args.
		String inputFile = null, solutionName = null;
		for (int i = 0; i < args.length; i++) {
			int sepCharIndex = args[i].indexOf('=');
			if (sepCharIndex > -1) {
				String param = args[i].substring(0, sepCharIndex);
				String value = args[i].substring(sepCharIndex + 1).trim();
				if (param.equals("-f")) {
					inputFile = value;
				}
				if (param.equals("-s")) {
					solutionName = value;
				}
			}
		}
		if (inputFile == null) {
			printUsage();
			System.exit(1);
		}
		if (solutionName == null) {
			solutionName = DEFAULT_SOLUTION;
		}
		ConferenceTrackManagement conferenceTrackManagement = new ConferenceTrackManagement();
		try {
			conferenceTrackManagement.initAllTalks(inputFile);
			conferenceTrackManagement.useSolution(solutionName);
			conferenceTrackManagement.schedule();
			System.out.println(conferenceTrackManagement);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}

	private List<Item> allTalks;
	private List<Track> allTracks;
	private Solution solution;

	public ConferenceTrackManagement() {
		this.allTracks = new LinkedList<Track>();
	}

	/**
	 * Create a solution by the solution name.
	 * 
	 * @param solutionName
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public void useSolution(String solutionName)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		solution = (Solution) Class
				.forName("com.thoughtworks.homeworks.ctm.solution." + solutionName.toUpperCase() + "Solution")
				.newInstance();
	}

	/**
	 * Use the solution to schedule the talks.
	 */
	public void schedule() {
		solution.pack(allTalks, new BinFactory() {

			private List<Bin> bins = new LinkedList<Bin>();

			public List<Bin> getBins() {
				return bins;
			}

			public List<Bin> createMoreBin() {
				Track track = new Track();
				track.setMorningSession(new Session(Track.MORNING_SESSION_MAX_DURATION));
				track.setAfternoonSession(new Session(Track.AFTERNOON_SESSION_MAX_DURATION));
				ConferenceTrackManagement.this.allTracks.add(track);
				bins.add(track.getMorningSession());
				bins.add(track.getAfternoonSession());
				List<Bin> newCreatedBins = new LinkedList<Bin>();
				newCreatedBins.add(track.getMorningSession());
				newCreatedBins.add(track.getAfternoonSession());
				return newCreatedBins;
			}

		});
	}

	private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("hh:mma");

	public String toString() {
		String lineChar = System.getProperty("line.separator");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < allTracks.size(); i++) {
			sb.append("Track " + (i + 1) + ":");
			sb.append(lineChar);
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, 9);
			calendar.set(Calendar.MINUTE, 0);
			Track track = (Track) allTracks.get(i);
			for (Talk talk : track.getMorningSession().getTalks()) {
				sb.append(TIME_FORMAT.format(calendar.getTime()) + " " + talk.getName());
				sb.append(lineChar);
				calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + talk.getDuration());
			}
			calendar.set(Calendar.HOUR_OF_DAY, 12);
			calendar.set(Calendar.MINUTE, 0);
			sb.append(TIME_FORMAT.format(calendar.getTime()) + " Lunch");
			sb.append(lineChar);
			calendar.set(Calendar.HOUR_OF_DAY, 13);
			calendar.set(Calendar.MINUTE, 0);
			for (Talk talk : track.getAfternoonSession().getTalks()) {
				sb.append(TIME_FORMAT.format(calendar.getTime()) + " " + talk.getName());
				sb.append(lineChar);
				calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + talk.getDuration());
			}
			if (calendar.get(Calendar.HOUR_OF_DAY) < 16) {
				calendar.set(Calendar.HOUR_OF_DAY, 16);
				calendar.set(Calendar.MINUTE, 0);
			}
			sb.append(TIME_FORMAT.format(calendar.getTime()) + " Networking Event");
			sb.append(lineChar);
			sb.append(lineChar);
		}
		return sb.toString();
	}

	/**
	 * Read the data from file and deserialize string to talk model.
	 * 
	 * @param file
	 * @throws Exception
	 */
	public void initAllTalks(String file) throws Exception {
		allTalks = new ArrayList<Item>();
		List<String> lines = Files.readAllLines(Paths.get(file));
		for (String line : lines) {
			int sepratIndex = line.lastIndexOf(' ');
			if (sepratIndex > -1) {
				String talkDuration = line.substring(sepratIndex + 1);
				int duration = 0;
				if (talkDuration.endsWith("min")) {
					sepratIndex = talkDuration.indexOf("min");
					duration = Integer.parseInt(talkDuration.substring(0, sepratIndex));
				} else if (talkDuration.equalsIgnoreCase("lightning")) {
					duration = 5;
				} else {
					throw new Exception("Invalid duration format:" + talkDuration);
				}
				Talk talk = new Talk();
				talk.setName(line);
				talk.setDuration(duration);
				allTalks.add(talk);
			} else {
				throw new Exception("Invalid data format:" + line);
			}
		}
	}
}
