package com.example.Podcast.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.Podcast.output.GeneralResponse;
import com.example.Podcast.output.ResponseStatus;

import java.util.Locale;

@Component
public class ResponseFactory {

	@Autowired
	private MessageSource messageSource;

	public ResponseEntity success() {
		GeneralResponse<Object> responseObject = new GeneralResponse();
		ResponseStatus responseStatus = new ResponseStatus();
		responseStatus.setCode(ResponseStatusEnum.SUCCESS.getCode());
		responseStatus.setMessage(ResponseStatusEnum.SUCCESS.getMessage());
		responseObject.setStatus(responseStatus);
		return ResponseEntity.ok(responseObject);
	}

	public <T> ResponseEntity<GeneralResponse<T>> success(T data) {
		GeneralResponse<T> responseObject = new GeneralResponse<>();
		ResponseStatus responseStatus = new ResponseStatus();
		responseStatus.setCode(ResponseStatusEnum.SUCCESS.getCode());
		responseStatus.setMessage(ResponseStatusEnum.SUCCESS.getMessage());
		responseObject.setStatus(responseStatus);
		responseObject.setData(data);
		return ResponseEntity.ok(responseObject);
	}

	public ResponseEntity error(HttpStatus httpStatus, String errorCode, String errorMessage) {
		ResponseStatus responseStatus = new ResponseStatus();
		GeneralResponse<Object> responseObject = new GeneralResponse();
		responseStatus.setCode(errorCode);
		responseStatus.setMessage(errorMessage);
		responseObject.setStatus(responseStatus);
		return new ResponseEntity(responseObject, httpStatus);
	}

	public ResponseEntity error(HttpStatus httpStatus, String errorCode, String errorMessage, Object data) {
		Locale locale = LocaleContextHolder.getLocale();
		ResponseStatus responseStatus = new ResponseStatus();
		GeneralResponse<Object> responseObject = new GeneralResponse();
		responseStatus.setCode(errorCode);
		responseStatus.setMessage(errorMessage);
		responseObject.setStatus(responseStatus);
		responseObject.setData(data);
		return new ResponseEntity(responseObject, httpStatus);
	}

	public ResponseEntity error(HttpStatus httpStatus, ResponseStatusEnum status) {
		ResponseStatus responseStatus = new ResponseStatus();
		GeneralResponse<Object> responseObject = new GeneralResponse();
		responseStatus.setCode(status.getCode());
		responseStatus.setMessage(status.getMessage());
		responseObject.setStatus(responseStatus);
		return new ResponseEntity(responseObject, httpStatus);
	}

	public ResponseEntity error(HttpStatus httpStatus, IResponseStatus status) {
		ResponseStatus responseStatus = new ResponseStatus();
		GeneralResponse<Object> responseObject = new GeneralResponse();
		responseStatus.setCode(status.getCode());
		responseStatus.setMessage(status.getMessage());
		responseObject.setStatus(responseStatus);
		return new ResponseEntity(responseObject, httpStatus);
	}

	public ResponseEntity error(HttpStatus httpStatus, IResponseStatus status, Object data) {
		ResponseStatus responseStatus = new ResponseStatus();
		GeneralResponse<Object> responseObject = new GeneralResponse();
		responseStatus.setCode(status.getCode());
		responseStatus.setMessage(status.getMessage());
		responseObject.setStatus(responseStatus);
		responseObject.setData(data);
		return new ResponseEntity(responseObject, httpStatus);
	}
}
