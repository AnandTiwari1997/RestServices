/**
 * 
 */
package com.impetus.restexample.model;

import java.io.Serializable;

import javax.ws.rs.Produces;

/**
 * @author anand.tiwari
 *
 */
@Produces("application/json")
public class Track implements Serializable {
	
	/**
	 * Default Version ID.
	 */
	private static final long serialVersionUID = 1L;

	String title;

	String singer;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	@Override
	public String toString() {
		return "Track [title=" + title + ", singer=" + singer + "]";
	}
	
}
