package com.jamesye.starter.realtimeserver.modules.chat;

public class ChatMessage {

	private Long id;
    private String userName;
    private String message;
    private String image;

    public ChatMessage() {
    }

    public ChatMessage(Long id, String userName, String message, String image) {
		super();
		this.id = id;
		this.userName = userName;
		this.message = message;
		this.image = image;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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
