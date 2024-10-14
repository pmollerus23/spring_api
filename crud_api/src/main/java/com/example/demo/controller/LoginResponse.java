package com.example.demo.controller;

public class LoginResponse {
    private String token;

    private long expiresIn;

    public String getToken() {
        return token;
    }
    
    public long getExpiresIn() {
    	return expiresIn;
    }
    
    public void setToken(String token) {
    	this.token = token;
    }

	public void setExpiresIn(long expirationTime) {
		// TODO Auto-generated method stub
		this.expiresIn = expirationTime;
	}

 // Getters and setters...
}
