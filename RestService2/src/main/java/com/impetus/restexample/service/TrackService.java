package com.impetus.restexample.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.impetus.restexample.model.Track;

/**
 * Web Service for getting and adding tracks.
 * 
 * @author Anand Tiwari
 *
 */
@Path("/TrackService")
public class TrackService {

	/**
	 * Static initializations.
	 */
	private static Logger logger = Logger.getLogger(TrackService.class);
	TrackRepository repository = new TrackRepository();

	/**
	 * Adding single track to the list.
	 * 
	 * @param track
	 *            Track object to be added.
	 * @return Response object.
	 */
	@POST
	@Path("/setTrack")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addtracks(Track track) {
		logger.debug(track);
		String result = repository.addTrack(track);
		return Response.status(200).entity(result).build();
	}

	/**
	 * Getting all the tracks.
	 * 
	 * @return List containing all the tracks.
	 */
	@GET
	@Path("/getTracks")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Track> getTracks() {
		logger.info(repository.getTracks());
		return repository.getTracks();
	}

	/**
	 * Getting track on the basis of title of the song.
	 * 
	 * @param title
	 *            song title.
	 * @return resultant track if found otherwise null.
	 */
	@GET
	@Path("/getTrack")
	@Produces(MediaType.APPLICATION_JSON)
	public Track getTrack(@QueryParam("title") String title) {
		logger.debug(title);
		return repository.getTrack(title);
	}
	
	@GET
	@Path("/check")
	@Produces(MediaType.APPLICATION_JSON)
	public String checkException() {
		throw new RuntimeException("Some Error Occurred.");
	}

}