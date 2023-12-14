package com.example.demo.DTO.post;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.nio.charset.MalformedInputException;

@Getter
@Setter
public class PostUpdateDTO extends PostCreateDTO{
    @Min(0)
    private Integer upVotes;
    @Min(0)
    private Integer downVotes;

    public PostUpdateDTO(long userId, String content, Integer upVotes, Integer downVotes) {
        super(userId, content);
        this.downVotes = downVotes;
        this.upVotes = upVotes;
    }

}
