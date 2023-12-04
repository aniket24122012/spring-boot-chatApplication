package com.instagram.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.instagram.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("select p from Post p where p.user.userId=:userId")
    List<Post> findPostByUserId(Integer userId);
}
