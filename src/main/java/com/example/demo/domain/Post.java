package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int upVotes;
    private int downVotes;
    private String content;

    @ManyToOne
    private UserObject user;

    protected Post(){

    }

    public Post(UserObject user, String content){
        this.user = user;
        this.upVotes = 0;
        this.downVotes = 0;
        this.content = content;
    }






}
