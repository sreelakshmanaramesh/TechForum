package com.techforum.usermicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techforum.usermicroservice.entity.Admin;
import com.techforum.usermicroservice.service.AdminService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/register")
	public ResponseEntity<Object> adminRegister(@RequestBody Admin admin) {
		Admin createdAdmin = adminService.adminRegister(admin);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdAdmin);
	}
	
	@GetMapping("/login/{email}/{password}")
	public ResponseEntity<Object> adminLogin(@PathVariable String email, @PathVariable String password) {
		Admin admin = adminService.adminLogin(email, password);
		return ResponseEntity.status(HttpStatus.OK).body(admin);
	}

	@GetMapping("/logout/{adminId}")
	public ResponseEntity<Object> adminLogout(@PathVariable Long adminId) {
		adminService.adminLogout(adminId);
		return ResponseEntity.status(HttpStatus.OK).body("Logout successfull");
	}

	@PostMapping("/changePassword/{adminId}/{oldPassword}/{newPassword}")
	public ResponseEntity<Object> changeAdminPassword(@PathVariable long adminId, @PathVariable String oldPassword, @PathVariable String newPassword) {
		Admin admin = adminService.changeAdminPassword(adminId, oldPassword, newPassword);
		return ResponseEntity.status(HttpStatus.OK).body(admin);
	}
}
