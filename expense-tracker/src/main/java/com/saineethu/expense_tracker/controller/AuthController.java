package com.saineethu.expense_tracker.controller;

import com.saineethu.expense_tracker.dto.AuthResponse;
import com.saineethu.expense_tracker.dto.UserDto;
import com.saineethu.expense_tracker.dto.UserLoginRequest;
import com.saineethu.expense_tracker.dto.UserRegisterRequest;
import com.saineethu.expense_tracker.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }
   /* @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserRegisterRequest request){
        UserDto user = authService.register(request);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody UserLoginRequest request){
        UserDto user = authService.login(request);
        return ResponseEntity.ok(user);
    }

    */

    @PostMapping("/register")
    public UserDto register(@RequestBody UserRegisterRequest request){
        return authService.register(request);
    }

  /*  @PostMapping("/login")
    public String login(@RequestBody UserLoginRequest request){
        return authService.login(request);
    }

   */

    @PostMapping("/login")
    public AuthResponse login (@RequestBody UserLoginRequest request){
        return authService.login(request);
    }
}
