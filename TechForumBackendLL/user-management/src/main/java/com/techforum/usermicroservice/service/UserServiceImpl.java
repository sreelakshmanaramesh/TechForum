package com.techforum.usermicroservice.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techforum.usermicroservice.entity.User;
import com.techforum.usermicroservice.exception.InvalidCredentialsException;
import com.techforum.usermicroservice.exception.UserAlreadyExistsException;
import com.techforum.usermicroservice.exception.ResourceNotFoundException;
import com.techforum.usermicroservice.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public User userRegister(User user) {
		
		User user1 = userRepo.findByEmail(user.getEmail());
		if (Objects.isNull(user1)) {
			return userRepo.save(user);
		}
		throw new UserAlreadyExistsException();
	}
	
	@Override
	public User userLogin(String email, String password) {

		User user = userRepo.findByEmail(email);
		if (Objects.isNull(user)) {
			throw new InvalidCredentialsException("User not found");
		}

		if (user.getPassword().equals(password)) {
			user.setActive(true);
			return userRepo.save(user);
		} else {
			throw new InvalidCredentialsException("Please check your credentials and try again");
		}
	}

	@Override
	public User userLogout(long userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new InvalidCredentialsException("Invalid session"));
		user.setActive(false);
		return userRepo.save(user);
	}

	@Override
	public User changeUserPassword(long userId, String oldPassword, String newPassword) {
		User user = userRepo.findById(userId).orElseThrow(() -> new InvalidCredentialsException("Invalid session"));
		if (user.getPassword().equals(oldPassword)) {
			user.setPassword(newPassword);
			return userRepo.save(user);
		} else {
			throw new InvalidCredentialsException("Please check your credentials and try again");
		}
	}
	
	@Override
	public User getUserById(Long id) {
		return userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}
	
	@Override
	public String deleteUserByEmail(String email) {
		User user = userRepo.findByEmail(email);
		if(user==null) {
			throw new ResourceNotFoundException("User not found");
		}else {
			userRepo.delete(user);
			return "User deleted";
		}
	}

}