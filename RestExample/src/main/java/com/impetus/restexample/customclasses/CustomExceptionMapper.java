package com.impetus.restexample.customclasses;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.impetus.restexample.model.Error;

@Provider
public class CustomExceptionMapper implements ExceptionMapper<RestException> {

	@Override
	public Response toResponse(RestException arg0) {		
		Error error = new Error(404, arg0.getMessage());
		return Response.status(Status.NOT_FOUND).entity(error).build();
	}


}
