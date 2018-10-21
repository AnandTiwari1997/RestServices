package com.impetus.restexample.customclasses;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.impetus.restexample.model.Error;

@Provider
public class CustomExceptionMapper implements ExceptionMapper<RestException> {

	@Override
	public Response toResponse(RestException restException) {
		Response.StatusType statusType = getStatusType(restException);
		Error error = new Error(statusType.getStatusCode(), restException.getMessage());
		return Response.status(error.getStatusCode()).entity(error).build();
	}

	private StatusType getStatusType(RestException restException) {
		return Response.Status.NOT_FOUND;
	}
}
