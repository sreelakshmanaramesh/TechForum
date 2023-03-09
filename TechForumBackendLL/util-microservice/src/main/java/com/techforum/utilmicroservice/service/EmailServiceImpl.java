package com.techforum.utilmicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.techforum.utilmicroservice.model.EmailDTO;

@Service
public class EmailServiceImpl implements EmailService{
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public String sendEmail(EmailDTO emailDTO) {
		System.out.print(emailDTO.getTo());
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("sreelakshmanarameshnakka999@gmail.com");
		mail.setTo(emailDTO.getTo());
		mail.setSubject(emailDTO.getSubject());
		mail.setText(emailDTO.getBody());
		javaMailSender.send(mail);
		return "Email sent";
	}
	
}
