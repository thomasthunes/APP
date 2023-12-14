package com.example.demo.repositories;

import com.example.demo.domain.Post;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface PostRepository extends CrudRepository<Post, Long> {
    Post getPostById(long Id);

    @Transactional
    void deletePostById(long Id);

    void deletePostByUserUserId(long Id);

    List<Post> findPostsByUserUserId(Long UserId);


}
