package com.techforum.utilmicroservice.util;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.techforum.utilmicroservice.model.EmailDTO;

public class EmailSender {
	
	public static ResponseEntity<EmailDTO> sendMail(EmailDTO emailDTO){
		String url = "http://localhost:8080/mail/sendEmail";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<EmailDTO> responseEntity = restTemplate.postForEntity(url, emailDTO, EmailDTO.class);
		return responseEntity;
	}
}
