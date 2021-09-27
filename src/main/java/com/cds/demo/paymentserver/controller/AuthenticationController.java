package com.cds.demo.paymentserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cds.demo.paymentserver.dto.AuthenticationRequest;
import com.cds.demo.paymentserver.dto.AuthenticationResponse;
import com.cds.demo.paymentserver.service.PaymentUserDetailService;
import com.cds.demo.paymentserver.util.JWTUtil;

@RestController
public class AuthenticationController {
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PaymentUserDetailService userDetailService;
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
		} 
		catch (BadCredentialsException e) {
			throw new BadCredentialsException("Incorrect username or password");
		}
		UserDetails userDetails = userDetailService.loadUserByUsername(request.getUserName());
		String jwt = jwtUtil.generateToken(userDetails);
		AuthenticationResponse response = AuthenticationResponse.builder().jwt(jwt).build();
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
}
