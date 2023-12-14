package com.example.demo.DTO.post;

import com.example.demo.domain.Post;
import com.example.demo.domain.UserObject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCreateDTO {

    @Min(1)
    private long userId;
    private String content;

    public PostCreateDTO(long userId, String content){
        this.userId = userId;
        this.content = content;
    }

    public static Post convertToPost(PostCreateDTO postDTO, UserObject user) {
        return new Post(user, postDTO.content);
    }

}
