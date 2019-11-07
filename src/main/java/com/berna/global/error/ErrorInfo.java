package com.berna.global.error;

import java.util.Date;

import lombok.Getter;


@Getter
public class ErrorInfo {
	
	private Date timestamp;
	private String message;
	private String details;

	  public ErrorInfo(Date timestamp, String message, String details) {
	    super();
	    this.timestamp = timestamp;
	    this.message = message;
	    this.details = details;
	  }

}
