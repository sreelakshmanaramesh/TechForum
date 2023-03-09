package com.techforum.usermicroservice.service;

import java.util.List;

import com.techforum.usermicroservice.entity.User;

public interface UserService {
	public User userRegister(User user);
	public User userLogin(String email, String password);
	public User userLogout(long userId);
	public User changeUserPassword(long adminId, String oldPassword, String newPassword);
	public User getUserByEmail(String email);
	public List<User> getAllUsers();
	public User getUserById(Long id);
	public String deleteUserByEmail(String email);
}
