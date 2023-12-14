package com.example.demo.services;

import com.example.demo.DTO.post.PostCreateDTO;
import com.example.demo.DTO.post.PostUpdateDTO;
import com.example.demo.controllers.exceptions.PostNotFoundException;
import com.example.demo.controllers.exceptions.UserNotFoundException;
import com.example.demo.domain.Post;
import com.example.demo.domain.Vote;

import java.util.List;

public interface IPostService {

    /**
     *
     * @param postCreateDTO
     * @return
     * @throws UserNotFoundException
     */
    Post createPost(PostCreateDTO postCreateDTO) throws UserNotFoundException;

    /**
     *
     * @param id
     * @return
     * @throws PostNotFoundException
     */
    Post getPostById(Long id) throws PostNotFoundException;

    /**
     *
     * @param userId
     * @return
     * @throws UserNotFoundException
     */
    List<Post> getPostByUserId(Long userId) throws UserNotFoundException;

    /**
     *
     * @param id
     * @param postUpdateDTO
     * @return
     * @throws PostNotFoundException
     */
    Post updatePost(Long id, PostUpdateDTO postUpdateDTO) throws PostNotFoundException;

    /**
     *
     * @param userId
     * @throws UserNotFoundException
     */
    void deletePostByUserId(Long userId) throws UserNotFoundException, PostNotFoundException;

    void deletePostById(Long id) throws UserNotFoundException, PostNotFoundException;

    /**
     *
     * @param id
     * @param vote
     * @return
     * @throws PostNotFoundException
     */
    Post addVote(long id, Vote vote) throws PostNotFoundException;
}
