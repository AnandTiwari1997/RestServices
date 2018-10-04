package com.impetus.restexample.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.impetus.restexample.model.Track;

/**
 * Repository for storing the tracks.
 * 
 * @author Anand Tiwari
 *
 */
public class TrackRepository {

	/**
	 * Static initialization of list.
	 */
	private static Logger logger = Logger.getLogger(TrackRepository.class);
	private File file;
	
	public TrackRepository() {
		String path ="C:\\Users\\Anand Tiwari\\Desktop\\Track_Record.txt";
		file = new File(path);
	}
	

	/**
	 * Adding track to list.
	 * 
	 * @param track
	 *            track object to be added.
	 * @return boolean true or false.
	 */
	public String addTrack(Track track) {
		boolean found = false;
		List<Track> list = getTracks();
		try(FileWriter fileWriter = new FileWriter(file);) {
			logger.info("Inside addTrack()");
			Gson gson = new Gson();
			for (Track track2 : list) {
				logger.info(track2);
				if (track2.getTitle().equalsIgnoreCase(track.getTitle())
						&& track2.getSinger().equalsIgnoreCase(track.getSinger())) {
					found = true;
				} 
				fileWriter.write(gson.toJson(track2));
				fileWriter.write("\n");	
			}
			if (!found) {
				fileWriter.write(gson.toJson(track));
				return "Track Added Successfully.";				
			} else {
				return "Track Already Exist.";
			}
		} catch(IOException e) {
			logger.error(e.getMessage());
		} 
		return "Some error occurred.";
	}

	/**
	 * Getting the list containing tracks.
	 * 
	 * @return list of track objects.
	 */
	public List<Track> getTracks() {
		List<Track> tracks = new ArrayList<>();
		try(FileReader fileReader = new FileReader(file);
				BufferedReader bufferedReader = new BufferedReader(fileReader);) {
			Track track;
			String object = bufferedReader.readLine();
			Gson gson = new Gson();
			do {
				track = gson.fromJson(object, Track.class);
				logger.info(track);
				tracks.add(track);
				object = bufferedReader.readLine();
			}while (object != null);
			logger.info(tracks);
		} catch(IOException e) {
			logger.error(e);
			logger.error("End Of File.....No more Objects to read.");
		} 
		return tracks;
	}

	/**
	 * Getting a single track based on the title.
	 * 
	 * @param title
	 *            title of the track.
	 * @return resultant track if found otherwise null.
	 */
	public Track getTrack(String title) {
		for (Track track : getTracks()) {
			if (track.getTitle().equals(title)) {
				logger.info(track);
				return track;
			}
		}
		return null;
	}

}
