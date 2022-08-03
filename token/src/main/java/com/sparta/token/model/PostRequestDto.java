package com.sparta.token.model;

import lombok.Getter;

@Getter
public class PostRequestDto {
    private String title;
    private String content;
    private String author;
    private String password;
}
