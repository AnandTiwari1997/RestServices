/**
 * 
 */
package com.impetus.restexample.model;

/**
 * @author Anand Tiwari
 *
 */
public class Error {
	
	private int statusCode;
	private String errorMessage;
	
	public Error(int statusCode, String errorMessage) {
		super();
		this.statusCode = statusCode;
		this.errorMessage = errorMessage;
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
