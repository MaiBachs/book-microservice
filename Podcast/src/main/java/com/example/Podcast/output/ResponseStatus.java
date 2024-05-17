package com.example.Podcast.output;


import com.example.Podcast.factory.IResponseStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseStatus implements IResponseStatus{
	private String code;
	private String message;
	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return this.code;
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return this.message;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ResponseStatus(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	public ResponseStatus() {
		super();
	}
	
	
}
