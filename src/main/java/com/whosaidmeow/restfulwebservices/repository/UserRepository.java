package com.whosaidmeow.restfulwebservices.repository;

import com.whosaidmeow.restfulwebservices.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
