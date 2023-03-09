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
import org.springframework.web.bind.annotation.RestController;

import com.techforum.usermicroservice.entity.User;
import com.techforum.usermicroservice.service.UserService;

@RestController
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping()
	public String test() {
		return "hi";
	}
	
	@PostMapping("/register")
	public ResponseEntity<Object> userRegister(@RequestBody User user) {
		User createdUser = userService.userRegister(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}
	
	@GetMapping("/login/{email}/{password}")
	public ResponseEntity<User> userLogin(@PathVariable String email, @PathVariable String password) throws Exception{
		User user = userService.userLogin(email, password);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	@GetMapping("/logout/{userId}")
	public ResponseEntity<Object> userLogout(@PathVariable long userId){
		userService.userLogout(userId);
		return ResponseEntity.status(HttpStatus.OK).body("Logout successfull");
	}
	
	@PostMapping("/changePassword/{userId}/{oldPassword}/{newPassword}")
	public ResponseEntity<Object> changeUserPassword(@PathVariable long userId, @PathVariable String oldPassword, @PathVariable String newPassword) {
		User user = userService.changeUserPassword(userId, oldPassword, newPassword);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	@GetMapping("/getUserById/{id}")
	public ResponseEntity<Object> getUser(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
	}
	
	@GetMapping("/getUserByEmail/{email}")
	public ResponseEntity<Object> getUser(@PathVariable String email){
		return ResponseEntity.status(HttpStatus.OK).body(userService.getUserByEmail(email));
	}
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<Object> getAllUsers(){
		return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
	}
	
	@DeleteMapping("/deleteUserByEmail/{email}")
	public ResponseEntity<Object> deleteUserByEmail(@PathVariable String email){
		return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUserByEmail(email));
	}
	
}
