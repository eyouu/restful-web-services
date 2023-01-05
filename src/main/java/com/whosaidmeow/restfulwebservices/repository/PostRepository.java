package com.whosaidmeow.restfulwebservices.repository;

import com.whosaidmeow.restfulwebservices.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
