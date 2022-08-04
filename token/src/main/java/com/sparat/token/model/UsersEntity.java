package com.sparat.token.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparat.token.dto.PostRequestDto;
import com.sparat.token.model.Timestamped;
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

  private String nickname;

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


  public UsersEntity(PostRequestDto requestDto) {
    this.title = requestDto.getTitle();
    this.content = requestDto.getContent();
    this.author = requestDto.getAuthor();
    this.password = requestDto.getPassword();
  }

  public void update(PostRequestDto requestDto) {
    this.title = requestDto.getTitle();
    this.content = requestDto.getContent();
    this.author = requestDto.getAuthor();
    this.password = requestDto.getPassword();
  }

  @Builder
  public UsersEntity(String userId, String password) {
    this.nickname = userId;
    this.password = password;
  }
}