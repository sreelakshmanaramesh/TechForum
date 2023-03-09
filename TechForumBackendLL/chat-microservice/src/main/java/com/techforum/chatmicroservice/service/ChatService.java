package com.techforum.chatmicroservice.service;

import java.util.List;

import com.techforum.chatmicroservice.entity.Message;

public interface ChatService {
	public Message sendMessage(Message message); 
	public List<Message> getMessages();
}
