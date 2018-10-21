package com.impetus.restexample.service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.impetus.restexample.customclasses.CustomExceptionMapper;
import com.impetus.restexample.customclasses.CustomReaderWriter;

@ApplicationPath("/rest")
public class RootApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<>();
		classes.add(TrackService.class);
		classes.add(CustomReaderWriter.class);
		classes.add(CustomExceptionMapper.class);
		return classes;
	}
	
}
