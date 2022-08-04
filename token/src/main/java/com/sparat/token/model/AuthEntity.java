package com.sparat.token.model;

import com.sparat.token.model.UsersEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Getter
@RequiredArgsConstructor
@Table(name = "auth")
@Entity
public class AuthEntity {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;

  private String refreshToken;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UsersEntity usersEntity;

  @Builder
  public AuthEntity(String refreshToken, UsersEntity usersEntity) {
    this.refreshToken = refreshToken;
    this.usersEntity = usersEntity;
  }
  public void refreshUpdate(String refreshToken) {
    this.refreshToken = refreshToken;
  }
}
