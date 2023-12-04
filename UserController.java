package com.instagram.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.instagram.entity.User;
import com.instagram.service.UserService;


@RestController
public class UserController {
    @Autowired
	UserService userService;
    
	@PostMapping("/users")
	public User CreateUser(@RequestBody User user) {
//		User newUser = new User();
//	    newUser.setFirstName(user.getFirstName());
//	    newUser.setLastName(user.getLastName());
//	    newUser.setEmail(user.getEmail());
//	    newUser.setId(user.getId());
//		return newUser;
		User registerUser = userService.registerUser(user);
		return registerUser;
	}
	
	@GetMapping("/users/{userId}")
	public User getUserById(@PathVariable("userId") Integer id) throws Exception {
		User user = userService.findUserById(id);
		return user;
	}
	
	@PutMapping("/users/{userId}")
	public User updateUser(@RequestBody User user,@PathVariable Integer userId) throws Exception {
		User updateUser = userService.updateUser(user, userId);
		return updateUser;
		
	}
	@PutMapping("/users/follow/{userId1}/{userId2}")
	public User followwUserHandler(@PathVariable Integer userId1,@PathVariable Integer userId2) throws Exception {
		User followUser = userService.followUser(userId1, userId2);
		return followUser;
	}
	
	@GetMapping("/users/search")
	public List<User> serachUser(@RequestParam("query") String query){
		List<User> serachUser = userService.serachUser(query);
		return serachUser;
	}
	
}
