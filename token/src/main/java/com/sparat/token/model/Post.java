package com.sparat.token.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparat.token.dto.UserRequest;
import com.sparat.token.dto.UsersDetailsImpl;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

@Getter
@RequiredArgsConstructor
@Entity
public class Post extends Timestamped{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String author;

    public Post(UsersDetailsImpl userDetails, PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.author = userDetails.getUsername();
    }

    public void update(UsersDetailsImpl usersDetails, PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.author = usersDetails.getUsername();
    }

}