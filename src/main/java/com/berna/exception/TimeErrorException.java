package com.berna.exception;

@SuppressWarnings("serial")
public class TimeErrorException extends RuntimeException  {
	public TimeErrorException(String message) {
        super(message);
    }
}
