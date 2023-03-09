package com.techforum.utilmicroservice.service;

import com.techforum.utilmicroservice.model.EmailDTO;

public interface EmailService {
	public String sendEmail(EmailDTO emailDTO);
}
