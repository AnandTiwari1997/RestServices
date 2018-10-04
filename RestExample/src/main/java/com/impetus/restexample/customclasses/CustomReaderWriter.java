package com.impetus.restexample.customclasses;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class CustomReaderWriter<T> implements MessageBodyReader<T>, MessageBodyWriter<T> {
	
	public static final Logger LOGGER = Logger.getLogger(CustomReaderWriter.class);
	public static final String PRETTY_PRINT = "pretty-print";
	
	@Context
	private UriInfo info;
	
	private Gson gson;
	private Gson prettyGson;
	
	public CustomReaderWriter() {
		GsonBuilder gsonBuilder = new GsonBuilder()
				.serializeNulls()
				.enableComplexMapKeySerialization();
		
		gson = gsonBuilder.create();
		prettyGson = gsonBuilder.setPrettyPrinting().create();
	}

	@Override
	public long getSize(T arg0, Class<?> arg1, Type arg2, Annotation[] arg3, MediaType arg4) {
		return -1;
	}

	@Override
	public boolean isWriteable(Class<?> arg0, Type arg1, Annotation[] arg2, MediaType arg3) {
		return true;
	}

	@Override
	public void writeTo(T arg0, Class<?> arg1, Type arg2, Annotation[] arg3, MediaType arg4,
			MultivaluedMap<String, Object> arg5, OutputStream arg6) throws IOException, WebApplicationException {
		PrintWriter printWriter = new PrintWriter(arg6);
		try {
			String json;
			if (info.getQueryParameters().containsKey(PRETTY_PRINT))
				json = prettyGson.toJson(arg0);
			else
				json = gson.toJson(arg0);
			
			printWriter.write(json);
			printWriter.flush();
		} finally {
			printWriter.close();
		}
		
	}

	@Override
	public boolean isReadable(Class<?> arg0, Type arg1, Annotation[] arg2, MediaType arg3) {
		return true;
	}

	@Override
	public T readFrom(Class<T> arg0, Type arg1, Annotation[] arg2, MediaType arg3, MultivaluedMap<String, String> arg4,
			InputStream arg5) throws IOException, WebApplicationException {
		InputStreamReader inputStreamReader = new InputStreamReader(arg5);
		try {
			return gson.fromJson(inputStreamReader, arg0);
		} finally {
			inputStreamReader.close();
		}
	}

}
