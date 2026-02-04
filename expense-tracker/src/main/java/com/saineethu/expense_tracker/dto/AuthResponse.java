package com.saineethu.expense_tracker.dto;

public class AuthResponse {

    private String token;
    private UserDto user;

    public AuthResponse(String token, UserDto user){
        this.token = token;
        this.user = user;
    }

    public String getToken(){
        return token;
    }

    public UserDto getUser(){
        return user;
    }
}
