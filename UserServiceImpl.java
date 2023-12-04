package com.instagram.serviceImpl;

import java.util.*;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instagram.entity.User;
import com.instagram.repo.UserRepo;
import com.instagram.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepo userRepo;

	@Override
	public User registerUser(User user) {
		// TODO Auto-generated method stub
//		User newUser = new User();
//	    newUser.setFirstName(user.getFirstName());
//	    newUser.setLastName(user.getLastName());
//	    newUser.setEmail(user.getEmail());
//	    newUser.setId(user.getId());	
		User save = userRepo.save(user);
		return save;
	}

	@Override
	public User findUserById(Integer userId) throws Exception {
		// TODO Auto-generated method stub
		Optional<User> user = userRepo.findById(userId);
		if(user.isPresent()) {
			return user.get();
		}
		throw new Exception("user not exit with userid : " + userId);
		
	}

	@Override
	public User findUserByEmail(String email) {
		// TODO Auto-generated method stub
		User user = userRepo.findByEmail(email);
		return user;
	}

	@Override
	public User followUser(Integer userId1, Integer userId2) throws Exception {
		// TODO Auto-generated method stub
		User user1 = findUserById(userId1);
		User user2 = findUserById(userId2);
	    user2.getFollower().add(user1.getUserId());
	    user1.getFollowing().add(user2.getUserId());
	    
	    userRepo.save(user1);
	    userRepo.save(user2);
		
		return user1;
	}


	@Override
	public User updateUser(User user,Integer userId) throws Exception {
		// TODO Auto-generated method stub
		Optional<User> user1 = userRepo.findById(userId);
		
		if(user1.isEmpty()) {
			throw new Exception("user not exit with userid : " + userId);
		}
		User oldUser = user1.get();
	    if(user.getFirstName()!=null) {
	    	oldUser.setFirstName(user.getFirstName());
	    }
	    if(user.getLastName()!=null) {
	    	oldUser.setLastName(user.getLastName());
	    }
	    if(user.getEmail()!=null) {
	    	oldUser.setEmail(user.getEmail());
	    }
	    User save = userRepo.save(oldUser);
		return save;
	}

	@Override
	public List<User> serachUser(String query) {
		// TODO Auto-generated method stub
		List<User> serachUser = userRepo.serachUser(query);
		return serachUser;
	}

}
