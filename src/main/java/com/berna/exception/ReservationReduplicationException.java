package com.berna.exception;

@SuppressWarnings("serial")
public class ReservationReduplicationException extends RuntimeException {
	public ReservationReduplicationException(String message) {
        super(message);
    }
}
