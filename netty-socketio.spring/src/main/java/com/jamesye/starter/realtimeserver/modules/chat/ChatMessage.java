package com.jamesye.starter.realtimeserver.modules.chat;

public class ChatMessage {

	private Long id;
    private String userName;
    private String message;

    public ChatMessage() {
    }

    public ChatMessage(String userName, String message, Long id) {
        super();
        this.userName = userName;
        this.message = message;
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
    public String toString() {
        return "ChatMessage{" +
                "userName='" + userName + '\'' +
                ", message='" + message + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
