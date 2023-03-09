package com.techforum.chatmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techforum.chatmicroservice.entity.Message;

public interface ChatRepo extends JpaRepository<Message, Long>{

}
