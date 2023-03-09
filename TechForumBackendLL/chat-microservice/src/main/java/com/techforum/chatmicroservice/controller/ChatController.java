package com.techforum.chatmicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techforum.chatmicroservice.entity.Message;
import com.techforum.chatmicroservice.service.ChatService;

@RestController
@CrossOrigin("http://localhost:4200")
public class ChatController {
	
	@Autowired
	private ChatService chatService;
	
	@GetMapping("/getMessages")
	public ResponseEntity<Object> getMessages() {
		return ResponseEntity.status(HttpStatus.OK).body(chatService.getMessages());
	}
	
	@PostMapping("/postMessage")
	public ResponseEntity<Object> sendMessage(@RequestBody Message message){
		return ResponseEntity.status(HttpStatus.OK).body(chatService.sendMessage(message));
	}
}
