package com.berna.global.error;


import java.util.Date;

import com.berna.global.error.exception.ReservationReduplicationException;
import com.berna.global.error.exception.TimeErrorException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
public class CustomExceptionHandler {

	@ExceptionHandler(Exception.class)
    public ErrorInfo handleException(Exception _ex,WebRequest _req) {
			
		ErrorInfo errorInfo =  new ErrorInfo(new Date(),_ex.getMessage(),_req.getDescription(false));
		return errorInfo;
		
    }
	

	@ExceptionHandler(ReservationReduplicationException.class)
    public ErrorInfo handleReservationReduplicationException(ReservationReduplicationException _ex,WebRequest _req) {
			
		ErrorInfo errorInfo =  new ErrorInfo(new Date(),_ex.getMessage(),_req.getDescription(false));
		return errorInfo;
		
    }
	

	@ExceptionHandler(TimeErrorException.class)
    public ErrorInfo handleReservationTimeErrorException(TimeErrorException _ex,WebRequest _req) {
			
		ErrorInfo errorInfo =  new ErrorInfo(new Date(),_ex.getMessage(),_req.getDescription(false));
		return errorInfo;
		
    }
}
