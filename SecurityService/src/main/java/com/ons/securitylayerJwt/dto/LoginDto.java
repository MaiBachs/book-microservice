package com.ons.securitylayerJwt.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginDto {

    //it's a Data Trasfer Object for Login
	private Integer userId;
    private String email ;
    private String password ;
    private String token;
    private String userName;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LoginDto(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public LoginDto() {
		super();
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public LoginDto(Integer userId, String email, String password, String token) {
		super();
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.token = token;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
    
    
}
