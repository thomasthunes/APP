package com.example.demo.DTO.post;

import com.example.demo.domain.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO {
    private long id;
    private long userId;
    private String content;
    private int upVote;
    private int downVote;

    public static PostDTO PostToDTO(Post post) {
        var dto = new PostDTO();
        dto.setId(post.getId());
        dto.setUserId(post.getUser().getUserId());
        dto.setContent(post.getContent());
        dto.setUpVote(post.getUpVotes());
        dto.setDownVote(post.getDownVotes());
        return dto;
    }
}
