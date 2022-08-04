package com.sparat.token.dto;

import lombok.Getter;

@Getter
public class UserRequest {
   private String title;
   private String content;
   private String author;
   private String password;
}
