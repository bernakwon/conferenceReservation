package com.berna.exception;

import java.util.Date;

import org.springframework.context.annotation.Configuration;

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
