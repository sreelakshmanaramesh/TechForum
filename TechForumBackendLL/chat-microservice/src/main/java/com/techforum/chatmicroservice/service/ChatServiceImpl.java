package com.techforum.chatmicroservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techforum.chatmicroservice.entity.Message;
import com.techforum.chatmicroservice.repository.ChatRepo;

@Service
public class ChatServiceImpl implements ChatService{
	
	@Autowired
	private ChatRepo chatRepo;

	@Override
	public List<Message> getMessages() {
		return chatRepo.findAll();
	}

	@Override
	public Message sendMessage(Message message) {
		return chatRepo.save(message);
	}
	
}
