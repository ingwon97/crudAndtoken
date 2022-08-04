package com.sparat.token.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparat.token.domains.auth.domain.UserDetailsImpl;
import com.sparat.token.dto.PostRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Post extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String author;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    public Post(UserDetailsImpl userDetails, PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.author = userDetails.getUsername();
        this.password = requestDto.getPassword();
    }

    public void update(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.author = requestDto.getAuthor();
        this.password = requestDto.getPassword();
    }
}
