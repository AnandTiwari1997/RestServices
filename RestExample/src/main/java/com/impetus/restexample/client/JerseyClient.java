package com.impetus.restexample.client;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyClient {

	/**
	 * Static initializations.
	 */
	private static Logger logger = Logger.getLogger(JerseyClient.class);
	private static final String FAILURE = "Unable to connect to the server";

	public static void main(String[] args) {

		try {
			
			String setTrackUrl = "http://localhost:8080/RestExample/TrackService/setTrack";
			String getTracksUrl = "http://localhost:8080/RestExample/TrackService/getTracks?pretty-print";
			String getTrackUrl = "http://localhost:8080/RestExample/TrackService/getTrack?pretty-print&title=";

			String jsonInput = "{" + "\"title\": \"KillShot\", " + "\"singer\": \"Eminem\"" + "}";
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("title", "Rap Devil");
			jsonObject.put("singer", "MGK");

			Client client = new Client();
			
			// adding track to the web service
			WebResource webResource = client.resource(setTrackUrl);
			ClientResponse clientResponse = webResource.type("application/json").post(ClientResponse.class,
					jsonInput);
			if (clientResponse.getStatus() != 200) {
				logger.error(FAILURE);
			}
			String output = clientResponse.getEntity(String.class);
			logger.info("response : " + output);
			
			// adding track to the web service
			WebResource webResource2 = client.resource(setTrackUrl);
			ClientResponse clientResponse2 = webResource2.type("application/json").post(ClientResponse.class,
					jsonObject);
			if (clientResponse2.getStatus() != 200) {
				logger.error(FAILURE);
			}
			String output2 = clientResponse2.getEntity(String.class);
			logger.info("response : " + output2);
			
			//fetching all the tracks.
			WebResource webResource3 = client.resource(getTracksUrl);
			ClientResponse clientResponse3 = webResource3.accept(MediaType.APPLICATION_JSON) //
		              .header("content-type", MediaType.APPLICATION_JSON).get(ClientResponse.class);
			if (clientResponse3.getStatus() != 200) {
				logger.error(FAILURE);
			}
			String output3 = clientResponse3.getEntity(String.class);
			logger.info("response: " + output3);
			
			
			// fetching a track based on title.
			String title = "Rap%20Devil";
			WebResource webResource4 = client.resource(getTrackUrl + title);
			ClientResponse clientResponse4 = webResource4.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
			if (clientResponse4.getStatus() != 200) {
				logger.error(FAILURE);
			}
			String output4 = clientResponse4.getEntity(String.class);
			logger.info("response: " + output4);
			
			
			// Some parsing geeks.
			JSONParser jsonParser = new JSONParser();
			org.json.simple.JSONArray object = (org.json.simple.JSONArray) jsonParser.parse(output3);
			logger.info(object);
			
			for (int i = 0; i < object.size(); i++) {
				org.json.simple.JSONObject object2 = (org.json.simple.JSONObject) object.get(i);
				logger.info(object2.get("singer"));
			}
			
		} catch (JSONException e) {
			logger.error("Unable to make JSON.");
		} catch (ParseException e) {
			logger.error("Unable to parse JSON.");
		}

	}

}
