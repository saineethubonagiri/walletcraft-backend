package com.saineethu.expense_tracker.service.impl;

import com.saineethu.expense_tracker.dto.AuthResponse;
import com.saineethu.expense_tracker.dto.UserDto;
import com.saineethu.expense_tracker.dto.UserLoginRequest;
import com.saineethu.expense_tracker.dto.UserRegisterRequest;
import com.saineethu.expense_tracker.entity.User;
import com.saineethu.expense_tracker.exception.EmailAlreadyExistsException;
import com.saineethu.expense_tracker.exception.InvalidCredentialsException;
import com.saineethu.expense_tracker.exception.UserAlreadyExistsException;
import com.saineethu.expense_tracker.mapper.UserMapper;
import com.saineethu.expense_tracker.repository.UserRepository;
import com.saineethu.expense_tracker.security.JwtUtil;
import com.saineethu.expense_tracker.service.AuthService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    //----------------REGISTER------------------
    @Override
    public UserDto register(UserRegisterRequest request){

        //1. Check if email exists
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new EmailAlreadyExistsException("Email already registered");
        }

        //2. Hash password
        String hashedPassword = passwordEncoder.encode(request.getPassword());

        //3. Create user entity
        User user = UserMapper.fromRegisterRequest(request, hashedPassword);

        //4. Save user
        User savedUser = userRepository.save(user);

        //5. Return DTO (NO PASSWORD)/ safe DTO
        return UserMapper.toDto(savedUser);
    }

    //----------------------LOGIN--------------------

    /*
    @Override
    public String login(UserLoginRequest request){

        //1. Find user by email
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        //2. Compare Password
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new InvalidCredentialsException("Invalid email or password");
        }

        //3. Return user DTO (for now)
        //return UserMapper.toDto(user);

        //3. Success (JWT comes later)
        return "login successful";
    }


     */

    @Override
    public AuthResponse login(UserLoginRequest request){

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){

            throw new InvalidCredentialsException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return  new AuthResponse(token, UserMapper.toDto(user));
    }
}
