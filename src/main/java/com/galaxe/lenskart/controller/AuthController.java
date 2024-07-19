package com.galaxe.lenskart.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.galaxe.lenskart.entity.JwtRequest;
import com.galaxe.lenskart.entity.JwtResponse;
import com.galaxe.lenskart.exceptionhandler.BadEmailPassException;
import com.galaxe.lenskart.security.JwtHelper;
import com.galaxe.lenskart.service.impl.CustomCustomerServiceImpl;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
    private CustomCustomerServiceImpl userDetailsService;

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private JwtHelper helper;
    


    private Logger logger = LoggerFactory.getLogger(AuthController.class);


    /**
     * Login authentication using jwt
     * @param request the email and password of the user
     * @return jwt response containing the jw token and username
     */
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        this.doAuthenticate(request.getEmail(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .userName(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Authentication method using UsernamePasswordAuthenticationToken from spring security
     * @param email the email of the user
     * @param password the password of the user
     * @throws BadEmailPassException the custom exception to handle bad email or password
     */
    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadEmailPassException(" Invalid Username or Password  !!");
        }
        

    }
    
 
    


 

}
