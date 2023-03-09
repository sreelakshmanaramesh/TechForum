package com.techforum.utilmicroservice.service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.techforum.utilmicroservice.model.EmailDTO;
import com.techforum.utilmicroservice.util.EmailSender;

@Service
public class OtpService {
	private static final Integer EXPIRE_MINS = 4;
	private LoadingCache<String, Integer> otpCache;

	public OtpService() {
		super();
		otpCache = CacheBuilder.newBuilder().expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES)
				.build(new CacheLoader<String, Integer>() {
					public Integer load(String key) {
						return 0;
					}
				});
	}

	public int generateOTP(String key) {
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		otpCache.put(key, otp);
		sendOtp(key, otp);
		return otp;
	}

	public int getOtp(String key) {
		try {
			return otpCache.get(key);
		} catch (Exception e) {
			return 0;
		}
	}

	public void clearOTP(String key) {
		otpCache.invalidate(key);
	}

	public String validateOtp(String key, int otp) {
		if (otp >= 0) {
			int serverOtp = getOtp(key);
			if (serverOtp > 0) {
				if (otp == serverOtp) {
					clearOTP(key);
					return "SUCCESS";
				} else {
					return "FAIL";
				}
			} else {
				return "Otp expired, Please try again";
			}
		} else {
			return "Please enter a valid Otp";
		}
	}
	
	public void sendOtp(String email, int otp) {
		EmailDTO emailDto = new EmailDTO();
		emailDto.setTo(email);
		emailDto.setSubject("Otp from TechForum");
		emailDto.setBody("Your Otp is " + otp);
		EmailSender.sendMail(emailDto);
	}
}
