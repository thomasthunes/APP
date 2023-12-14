package com.example.demo.services;


import com.example.demo.DTO.post.PostCreateDTO;
import com.example.demo.DTO.post.PostUpdateDTO;
import com.example.demo.controllers.exceptions.PostNotFoundException;
import com.example.demo.controllers.exceptions.UserNotFoundException;
import com.example.demo.domain.Post;
import com.example.demo.domain.UserObject;
import com.example.demo.domain.Vote;
import com.example.demo.repositories.PostRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService implements IPostService{
    private final UserRepository userRepository;

    private final PostRepository postRepository;

    @Autowired
    public PostService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public Post createPost(PostCreateDTO postCreateDTO) throws UserNotFoundException {
        UserObject user = userRepository.findByUserId(postCreateDTO.getUserId());
        if (user == null){
            throw new UserNotFoundException(postCreateDTO.getUserId());
        }
        Post post = PostCreateDTO.convertToPost(postCreateDTO, user);
        return postRepository.save(post);
    }

    @Override
    public Post getPostById(Long id) throws PostNotFoundException {
        var post = postRepository.getPostById(id);
        if (post == null) {
            throw new PostNotFoundException(id);
        }
        return post;
    }

    @Override
    public List<Post> getPostByUserId(Long userId) throws UserNotFoundException {
        var user = userRepository.findByUserId(userId);
        if (user == null){
            throw new UserNotFoundException(userId);
        }
        return postRepository.findPostsByUserUserId(userId);
    }

    @Override
    public Post updatePost(Long id, PostUpdateDTO postUpdateDTO) throws PostNotFoundException {
        Post existingPost = getPostById(id);

        if (postUpdateDTO.getContent() == null || postUpdateDTO.getContent().isEmpty()) {
            existingPost.setContent(postUpdateDTO.getContent());
        }

        if (postUpdateDTO.getUpVotes() != null){
            existingPost.setUpVotes(postUpdateDTO.getUpVotes());
        }
        if (postUpdateDTO.getDownVotes() != null) {
            existingPost.setDownVotes(postUpdateDTO.getDownVotes());
        }
        postRepository.save(existingPost);
        return existingPost;
    }

    @Override
    public void deletePostByUserId(Long userId) throws UserNotFoundException, PostNotFoundException {
        var user = userRepository.findByUserId(userId);
        if (user == null) throw new UserNotFoundException(userId);
        postRepository.deletePostByUserUserId(userId);
    }

    @Override
    public void deletePostById(Long id) throws UserNotFoundException, PostNotFoundException {
        var post = postRepository.getPostById(id);
        if (post == null) throw new PostNotFoundException(id);
        postRepository.deletePostById(id);
    }

    @Override
    public Post addVote(long id, Vote vote) throws PostNotFoundException {
        Post existingPost = getPostById(id);
        if (vote == Vote.UPVOTE) {
            existingPost.setUpVotes(existingPost.getUpVotes()+1);
        }
        if (vote == Vote.DOWNVOTE) {
            existingPost.setDownVotes(existingPost.getDownVotes()+1);
        }

        postRepository.save(existingPost);
        return existingPost;
    }
}
