package com.techforum.utilmicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techforum.utilmicroservice.model.EmailDTO;
import com.techforum.utilmicroservice.service.EmailService;

@RestController
@RequestMapping("/mail")
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	@PostMapping("/sendEmail")
	public ResponseEntity<Object> sendEmail(@RequestBody EmailDTO emailDTO){
		return ResponseEntity.status(HttpStatus.OK).body(emailService.sendEmail(emailDTO));
	}
}
