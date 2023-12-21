package com.achavez.adminsys.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.achavez.adminsys.exceptions.GeneralServiceException;
import com.achavez.adminsys.exceptions.NoDataFoundException;
import com.achavez.adminsys.exceptions.ValidateServiceException;
import com.achavez.adminsys.utils.WrapperResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ErrorHandlerConfig extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> all(Exception e, WebRequest req) {
		log.error(e.getMessage(), e);
		WrapperResponse<?> res = new WrapperResponse<>(false, "Internal Server Error", null);
		return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(ValidateServiceException.class)
	public ResponseEntity<?> validateService(ValidateServiceException e, WebRequest req) {
		log.info(e.getMessage(), e);
		WrapperResponse<?> res = new WrapperResponse<>(false, e.getMessage(), null);
		return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(NoDataFoundException.class)
	public ResponseEntity<?> noData(NoDataFoundException e, WebRequest req) {
		log.info(e.getMessage(), e);
		WrapperResponse<?> res = new WrapperResponse<>(false, e.getMessage(), null);
		return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler(GeneralServiceException.class)
	public ResponseEntity<?> generalService(GeneralServiceException e, WebRequest req) {
		log.error(e.getMessage(), e);
		WrapperResponse<?> res = new WrapperResponse<>(false, "Internal Server Error", null);
		return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
