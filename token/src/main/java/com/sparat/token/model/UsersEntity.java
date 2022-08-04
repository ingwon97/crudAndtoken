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
  private String author;

  @JsonIgnore
  @Column(nullable = false)
  private String password;


  @Builder
  public UsersEntity(String author, String password) {
    this.author = author;
    this.password = password;
  }

  public UsersEntity(UserRequest requestDto) {
    this.author = requestDto.getAuthor();
    this.password = requestDto.getPassword();
  }

}