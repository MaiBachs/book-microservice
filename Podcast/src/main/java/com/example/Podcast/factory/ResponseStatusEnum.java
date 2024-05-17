package com.example.Podcast.factory;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

public enum ResponseStatusEnum implements IResponseStatus {
    SUCCESS(ResponseStatusCodeConstant.SUCCESS, "Success"),
    GENERAL_ERROR(ResponseStatusCodeConstant.GENERAL_ERROR, "Any error occur"),
    ERROR(ResponseStatusCodeConstant.GENERAL_ERROR, "Invalid request"),
    NOT_FOUND(ResponseStatusCodeConstant.GENERAL_ERROR, "404 Not found"),
    FIELD_MISSING(ResponseStatusCodeConstant.FIELD_MISSING, "%s is required"),
    FIELD_EMPTY(ResponseStatusCodeConstant.FILE_EMPTY, "File is empty"),
    FIELD_FORMAT(ResponseStatusCodeConstant.FILE_FORMAT, "The file is not in the correct excel format"),
    FIELD_TOO_LARGE(ResponseStatusCodeConstant.FIELD_TOO_LARGE, "File size too large"),
    RECORD_ALREADY_EXISTS(ResponseStatusCodeConstant.RECORD, "record already exists");

    private String code;
    private String message;

    public static ResponseStatusEnum fromCode(String code) {
        for (ResponseStatusEnum s : ResponseStatusEnum.values()) {
            if (s.getCode().equalsIgnoreCase(code)) {
                return s;
            }
        }
        return GENERAL_ERROR;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

	public void setCode(String code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private ResponseStatusEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}

	private ResponseStatusEnum() {
	}
    
    
}
