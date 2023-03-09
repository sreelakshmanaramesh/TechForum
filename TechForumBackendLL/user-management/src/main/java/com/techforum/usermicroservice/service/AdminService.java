package com.techforum.usermicroservice.service;

import com.techforum.usermicroservice.entity.Admin;

public interface AdminService {
	public Admin adminRegister(Admin admin);
	public Admin adminLogin(String email, String password);
	public Admin adminLogout(Long adminId);
	public Admin changeAdminPassword(long adminId, String oldPassword, String newPassword);
}
