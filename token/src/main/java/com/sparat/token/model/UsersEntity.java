package com.sparat.token.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparat.token.dto.UserRequest;
import lombok.*;

import javax.persistence.*;

@Getter
@RequiredArgsConstructor
@Table(name = "users")
@Entity
public class UsersEntity extends Timestamped{

  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private Long id;

  @Column(nullable = false)
  private String role = "ROLE_MEMBER";

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String content;

  @Column(nullable = false)
  private String author;

  @JsonIgnore
  @Column(nullable = false)
  private String password;


  @Builder
  public UsersEntity(String title, String content, String author, String password) {
    this.title = title;
    this.content = content;
    this.author = author;
    this.password = password;
  }

  public UsersEntity(UserRequest requestDto) {
    this.title = requestDto.getTitle();
    this.content = requestDto.getContent();
    this.author = requestDto.getAuthor();
    this.password = requestDto.getPassword();
  }

  public void update(UserRequest requestDto) {
    this.title = requestDto.getTitle();
    this.content = requestDto.getContent();
    this.author = requestDto.getAuthor();
    this.password = requestDto.getPassword();
  }

  /*@Builder
  public UsersEntity(String userId, String password) {
    this.nickname = userId;
    this.password = password;
  }*/
}