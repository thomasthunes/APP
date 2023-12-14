package com.example.demo.DTO.post;

import com.example.demo.domain.Vote;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddVoteDTO {
    private @Valid Vote vote;
}
