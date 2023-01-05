package com.whosaidmeow.restfulwebservices.controller;

import com.whosaidmeow.restfulwebservices.exception.UserNotFoundException;
import com.whosaidmeow.restfulwebservices.model.Post;
import com.whosaidmeow.restfulwebservices.model.User;
import com.whosaidmeow.restfulwebservices.repository.PostRepository;
import com.whosaidmeow.restfulwebservices.repository.UserRepository;
import com.whosaidmeow.restfulwebservices.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;
import static java.util.Objects.isNull;

@RestController
@RequiredArgsConstructor
public class UserJpaController {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @GetMapping("/jpa/users")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> findById(@PathVariable int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(format("User with id: %d not found!", id)));

        Link link = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findAll())
                .withRel("all-users");

        return EntityModel.of(user).add(link);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> findPostsForUser(@PathVariable int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(format("User with id: %d not found!", id)));

        return user.getPosts();
    }

    @GetMapping("/jpa/posts/{postId}")
    public EntityModel<Post> findPostById(@PathVariable int postId) {
        Post post = postRepository.findById(postId).orElse(null);

        Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findAllPosts())
                .withRel("all-posts");

        return EntityModel.of(post).add(link);
    }

    @GetMapping("/jpa/posts")
    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userId}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).body(savedUser);
    }

    @PostMapping("/jpa/users/{userId}/posts")
    public ResponseEntity<Post> savePost(@PathVariable int userId,
                                         @Valid @RequestBody Post post) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(format("User with id: %d not found!", userId)));
        post.setUser(user);

        Post savedPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/jpa/posts/{postId}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        return ResponseEntity.created(location).body(savedPost);
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }
}
