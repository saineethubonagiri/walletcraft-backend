package com.saineethu.expense_tracker.service;

import com.saineethu.expense_tracker.dto.AuthResponse;
import com.saineethu.expense_tracker.dto.UserDto;
import com.saineethu.expense_tracker.dto.UserLoginRequest;
import com.saineethu.expense_tracker.dto.UserRegisterRequest;

public interface AuthService {

    UserDto register(UserRegisterRequest request);
    //String login(UserLoginRequest request);


    AuthResponse login(UserLoginRequest request);
}
