package com.example.Podcast.output;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GeneralResponse<T> implements Serializable {
	private ResponseStatus status;
	private T data;
	public ResponseStatus getStatus() {
		return status;
	}
	public void setStatus(ResponseStatus status) {
		this.status = status;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public GeneralResponse(ResponseStatus status, T data) {
		super();
		this.status = status;
		this.data = data;
	}
	public GeneralResponse() {
		super();
	}
	@Override
	public String toString() {
		return "GeneralResponse [status=" + status + ", data=" + data + "]";
	}
}
