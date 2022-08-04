package com.sparat.token.controller;

import com.sparat.token.service.UserService;
import com.sparat.token.dto.TokenResponse;
import com.sparat.token.dto.UserRequest;
import com.sparat.token.model.UsersEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {

  private final UserService userService;

  // 회원가입
  @PostMapping("/user/signUp")
  public ResponseEntity signUp(@RequestBody UserRequest userRequest) {
    return userService.findByAuthor(userRequest.getAuthor()).isPresent()
        ? ResponseEntity.badRequest().build()
        : ResponseEntity.ok(userService.signUp(userRequest));
  }

  // 로그인
  @PostMapping("/user/signIn")
  public ResponseEntity<TokenResponse> signIn(@RequestBody UserRequest userRequest) throws Exception {

    return ResponseEntity.ok().body(userService.signIn(userRequest));
  }

  @GetMapping("/info")
  public ResponseEntity<List<UsersEntity>> findUser() {
    return ResponseEntity.ok().body(userService.findUsers());
  }
}
