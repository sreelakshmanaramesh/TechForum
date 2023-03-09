package com.techforum.usermicroservice.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techforum.usermicroservice.entity.Admin;
import com.techforum.usermicroservice.exception.InvalidCredentialsException;
import com.techforum.usermicroservice.exception.UserAlreadyExistsException;
import com.techforum.usermicroservice.repository.AdminRepo;
import com.techforum.usermicroservice.repository.UserRepo;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminRepo adminRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public Admin adminRegister(Admin admin) {
		
		Admin admin1 = adminRepo.findByEmail(admin.getEmail());
		if (Objects.isNull(admin1)) {
			return adminRepo.save(admin);
		}
		throw new UserAlreadyExistsException();
	}
	
	@Override
	public Admin adminLogin(String email, String password) {

		Admin admin = adminRepo.findByEmail(email);
		if (Objects.isNull(admin)) {
			throw new InvalidCredentialsException("Invalid session");
		}
		if (admin.getPassword().equals(password)) {
			admin.setIsActive(true);
			adminRepo.save(admin);
		} else {
			throw new InvalidCredentialsException("Please check your credentials and try again");
		}
		return admin;
	}

	@Override
	public Admin adminLogout(Long adminId) {

		Admin admin = adminRepo.findById(adminId).orElseThrow(() -> new InvalidCredentialsException("Invalid session"));
		admin.setIsActive(false);
		return adminRepo.save(admin);
	}


	@Override
	public Admin changeAdminPassword(long adminId, String oldPassword, String newPassword) {
		
		Admin admin = adminRepo.findById(adminId).orElseThrow(() -> new InvalidCredentialsException("Invalid session"));
		if (admin.getPassword().equals(oldPassword)) {
			admin.setPassword(newPassword);
			return adminRepo.save(admin);
		} else {
			throw new InvalidCredentialsException("Please check your credentials and try again");
		}
	}
}
