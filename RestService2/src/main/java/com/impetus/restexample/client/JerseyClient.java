package com.impetus.restexample.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.impetus.restexample.model.Track;

public class JerseyClient {

	/**
	 * Static initializations.
	 */
	private static Logger logger = Logger.getLogger(JerseyClient.class);
	private static final String FAILURE = "Unable to connect to the server";

	public static void main(String[] args) {
		
		String jsonInput = "{" + "\"title\": \"KillShot\", " + "\"singer\": \"Eminem\"" + "}";
		
		Client client = ClientBuilder.newClient();
		
		
		Response response = client.target("http://localhost:8080/RestService2/rest/TrackService")
				.path("setTrack")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(jsonInput, MediaType.APPLICATION_JSON));
		
		logger.info(response);
		logger.info(response.readEntity(String.class));
		
		
		Response response1 = client.target("http://localhost:8080/RestService2/rest/TrackService")
			.path("getTracks")
			.queryParam("pretty-print")
			.request(MediaType.APPLICATION_JSON)
			.get();
		
		logger.info(response1);
		//logger.info(response.readEntity(new GenericType<List<Track>>() {}));
		List<Track> tracks = response1.readEntity(List.class);
		logger.info(tracks.toString());
		
		Response response2 = client.target("http://localhost:8080/RestService2/rest/TrackService")
				.path("getTrack")
				.queryParam("title", "KillShot")
				.request(MediaType.APPLICATION_JSON)
				.get();
		
		logger.info(response2);
		logger.info(response2.readEntity(String.class));


	}

}
