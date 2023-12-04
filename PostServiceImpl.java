package com.instagram.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instagram.entity.Post;
import com.instagram.entity.User;
import com.instagram.repo.PostRepository;
import com.instagram.repo.UserRepo;
import com.instagram.service.PostService;
import com.instagram.service.UserService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepository postRepo;

	@Autowired
	UserService userService;
	
	@Autowired
	UserRepo userRepo;

	@Override
	public Post createNewPost(Post post, Integer userId) throws Exception {

		// TODO Auto-generated method stub

		User user = userService.findUserById(userId);
		Post newPost = new Post();
		newPost.setCaption(post.getCaption());
		newPost.setImageUrl(post.getImageUrl());
		newPost.setCreateAt(LocalDateTime.now());
		newPost.setVideo(post.getVideo());
		newPost.setUser(user);

		return postRepo.save(newPost);
	}

	@Override
	public String deletePost(Integer postId, Integer userId) throws Exception {
		// TODO Auto-generated method stub
		Post post = findPostById(postId);
		User user = userService.findUserById(userId);
		if (post.getUser().getUserId() != user.getUserId()) {
			throw new Exception("you can't delete another users post");
		}

		postRepo.deleteById(postId);

		return "Post delete successfully";
	}

	@Override
	public List<Post> findPostByUserId(Integer userId) {
		// TODO Auto-generated method stub
		List<Post> findPostByUserId = postRepo.findPostByUserId(userId);
		return findPostByUserId;
	}

	@Override
	public Post findPostById(Integer postId) throws Exception {
		// TODO Auto-generated method stub
		Optional<Post> findById = postRepo.findById(postId);
		if (findById.isEmpty()) {
			throw new Exception("post not found with id : " + postId);
		}
		return findById.get();
	}

	@Override
	public List<Post> findAllPost() {
		// TODO Auto-generated method stub
		return postRepo.findAll();
	}

	@Override
	public Post savePost(Integer postId, Integer userId) throws Exception {
		// TODO Auto-generated method stub
		Post post = findPostById(postId);
		User user = userService.findUserById(userId);
		
		if(user.getSavePost().contains(post)) {
			user.getSavePost().remove(post);
		}else {
			user.getSavePost().add(post);
		}
		userRepo.save(user);
		return post;
	}

	@Override
	public Post likePost(Integer postId, Integer userId) throws Exception {
		// TODO Auto-generated method stub

		Post post = findPostById(postId);
		User user = userService.findUserById(userId);

		if(post.getLiked().contains(user)) {
			post.getLiked().remove(user);
		}else {
			post.getLiked().add(user);
		}
		
		return postRepo.save(post);
	}

}
