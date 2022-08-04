package com.sparat.token.domains.users.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparat.token.dto.PostRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Getter
@RequiredArgsConstructor
@Table(name = "users")
@Entity
public class UsersEntity {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;

  private String userId;

  private String password;
  private String nickname;
  private String Role = "ROLE_MEMBER";

  @Builder
  public UsersEntity(String userId, String password) {
    this.userId = userId;
    this.password = password;
  }


}
