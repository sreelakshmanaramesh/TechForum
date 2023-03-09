package com.techforum.utilmicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.techforum.utilmicroservice.service.OtpService;

@RestController
public class OtpController {

	@Autowired
	OtpService otpService;

	@GetMapping("/generateOtp/{emailId}")
	public ResponseEntity<Object> generateOtp(@PathVariable String emailId) {
		return ResponseEntity.status(HttpStatus.OK).body(otpService.generateOTP(emailId));
	}

	@GetMapping("/validateOtp/{emailId}/{otp}")
	public ResponseEntity<Object> validateOtp(@PathVariable String emailId, @PathVariable int otp) {
		return ResponseEntity.status(HttpStatus.OK).body(otpService.validateOtp(emailId, otp));
	}
}
