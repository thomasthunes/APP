package com.example.demo.controllers;

import com.example.demo.DTO.post.AddVoteDTO;
import com.example.demo.DTO.post.PostCreateDTO;
import com.example.demo.DTO.post.PostDTO;
import com.example.demo.DTO.post.PostUpdateDTO;
import com.example.demo.controllers.exceptions.PostNotFoundException;
import com.example.demo.controllers.exceptions.UserNotFoundException;
import com.example.demo.domain.Post;
import com.example.demo.services.IPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Validated
@CrossOrigin
public class PostController {

    private final IPostService postService;

    public PostController(IPostService postService) {
        this.postService = postService;
    }

    @Operation(summary = "Create a new post")
    @PostMapping("/post")
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostCreateDTO postDTO) throws UserNotFoundException {
        Post post = postService.createPost(postDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(PostDTO.PostToDTO(post));
    }

    @Operation(summary = "Get a post by its id")
    @GetMapping("/post/{id}")
    public ResponseEntity<PostDTO> getPostById(@Parameter(description = "The post id") @PathVariable @Min(1) long id) throws PostNotFoundException {
        Post post = postService.getPostById(id);
        return ResponseEntity.ok(PostDTO.PostToDTO(post));
    }

    @Operation(summary = "Get a list of posts belonging to a given user")
    @GetMapping("/post")
    public ResponseEntity<List<PostDTO>> getPostByUserId(@Parameter(description = "The user id") @RequestParam(name = "userId") @Min(1) long userId) throws UserNotFoundException {
        List<Post> posts = postService.getPostByUserId(userId);
        if (!posts.isEmpty()) {
            List<PostDTO> postDTOs = posts.stream().map(PostDTO::PostToDTO).collect(Collectors.toList());
            return ResponseEntity.ok(postDTOs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Update a post by its id")
    @PutMapping("/post/{id}")
    public ResponseEntity<PostDTO> updatePost(@Parameter(description = "The post id") @PathVariable @Min(1) long id, @Valid PostUpdateDTO postUpdateDTO) throws PostNotFoundException {
        var updatePost = postService.updatePost(id, postUpdateDTO);
        return ResponseEntity.ok(PostDTO.PostToDTO(updatePost));
    }

    @Operation(summary = "Update vote")
    @PostMapping("/post/{id}")
    public ResponseEntity<PostDTO> addVoteToPost(@Parameter(description = "The post id") @PathVariable @Min(1) long id, @Valid @RequestBody AddVoteDTO vote) throws PostNotFoundException {
        var updatedPost = postService.addVote(id, vote.getVote());
        return ResponseEntity.ok(PostDTO.PostToDTO(updatedPost));
    }

    @Operation(summary = "Delete a post by its id")
    @DeleteMapping("/post/{id}")
    public ResponseEntity<Void> deletePost(@Parameter(description = "The post id") @PathVariable @Min(1) long id) throws PostNotFoundException, UserNotFoundException {
        postService.deletePostById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Delete all posts belonging to a given user")
    @DeleteMapping("/post")
    public ResponseEntity<Void> deleteAllPollsBysUserId(@Parameter(description = "The user id") @RequestParam(name = "userId") @Min(1) long userId) throws UserNotFoundException, PostNotFoundException {
        postService.deletePostByUserId(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
