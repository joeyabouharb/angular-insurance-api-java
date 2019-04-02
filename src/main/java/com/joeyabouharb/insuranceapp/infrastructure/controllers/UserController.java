package com.joeyabouharb.insuranceapp.infrastructure.controllers;

import com.joeyabouharb.insuranceapp.infrastructure.payloads.LoginForm;

import com.joeyabouharb.insuranceapp.infrastructure.models.User;
import com.joeyabouharb.insuranceapp.infrastructure.payloads.ApiResponse;
import com.joeyabouharb.insuranceapp.infrastructure.payloads.JwtAuthenticationResponse;
import com.joeyabouharb.insuranceapp.infrastructure.repostories.UserRepository;
import com.joeyabouharb.insuranceapp.security.JwtTokenProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping(value = "/api/user")
@CrossOrigin(origins = "https://claim-app-angular.herokuapp.com/")
public class UserController {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;
  
@PostMapping(value="/login")
public ResponseEntity<?> authenticateUser(@RequestBody LoginForm loginForm) {

  System.out.println("hello");
  Authentication authentication = authenticationManager.authenticate(
    new UsernamePasswordAuthenticationToken(
            loginForm.getUsernameOrEmail(),
            loginForm.getPassword()
    )
);

SecurityContextHolder.getContext().setAuthentication(authentication);

String jwt = tokenProvider.generateToken(authentication);
return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));

}

@PostMapping("/signup")
public ResponseEntity<?> registerUser(@RequestBody User signUpRequest) {
    if(userRepository.existsByUsername(signUpRequest.getUsername())) {
        return ResponseEntity.badRequest().body(new ApiResponse(false, "Username is already taken!"));
    }

    if(userRepository.existsByEmail(signUpRequest.getEmail())) {
        return ResponseEntity.badRequest().body(new ApiResponse(false, "Email Address already in use!"));
    }

    // Creating user's account
    User user = new User( signUpRequest.getUsername(),
            signUpRequest.getEmail(), signUpRequest.getPassword(), signUpRequest.getRole());

    user.setPassword(passwordEncoder.encode(user.getPassword()));

    userRepository.save(user);


    return ResponseEntity.ok(new ApiResponse(true, "User registered successfully"));
}

}
