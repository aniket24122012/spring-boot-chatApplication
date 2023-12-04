package com.instagram.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity 
@Table(name = "post_of_user")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
	
	private String caption;
	
	private String imageUrl;
	
	private String video;
	
	@ManyToOne
	private User user;
	
	private LocalDateTime createAt;
	
	@OneToMany
	private List<User> liked = new ArrayList<>();

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public List<User> getLiked() {
		return liked;
	}

	public void setLiked(List<User> liked) {
		this.liked = liked;
	}

	public Post(Integer postId, String caption, String imageUrl, String video, User user, LocalDateTime createAt,
			List<User> liked) {
		super();
		this.postId = postId;
		this.caption = caption;
		this.imageUrl = imageUrl;
		this.video = video;
		this.user = user;
		this.createAt = createAt;
		this.liked = liked;
	}

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
