package com.sparat.token.controller;

import com.sparat.token.dto.ResponseDto;
import com.sparat.token.dto.UserRequest;
import com.sparat.token.dto.UsersDetailsImpl;
import com.sparat.token.dto.passwordDto;
import com.sparat.token.model.PostRequestDto;
import com.sparat.token.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping("/api/post")
    public ResponseDto<?> createPost(@AuthenticationPrincipal UsersDetailsImpl usersDetails,
                                     @RequestBody PostRequestDto requestDto) {
        return postService.createPost(usersDetails, requestDto);
    }

    @GetMapping("/api/post/{id}")
    public ResponseDto<?> getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @GetMapping("/api/post")
    public ResponseDto<?> getAllPosts() {
        return postService.getAllPost();
    }

    @PutMapping("/api/post/{id}")
    public ResponseDto<?> updatePost(@AuthenticationPrincipal UsersDetailsImpl usersDetails,
                                     @PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.updatePost(usersDetails, id, requestDto);
    }

    @DeleteMapping("/api/post/{id}")
    public ResponseDto<?> deletePost(@AuthenticationPrincipal UsersDetailsImpl usersDetails,
                                     @PathVariable Long id) {
        return postService.deletePost(usersDetails, id);
    }

    @PostMapping("/api/post/{id}")
    public ResponseDto<?> validateAuthorByPassword(@AuthenticationPrincipal UsersDetailsImpl usersDetails,
                                                   @PathVariable Long id) {
        return postService.validateAuthorByPassword(usersDetails, id);
    }

    @GetMapping("/whoami")
    public String whoAmI(@AuthenticationPrincipal UsersDetailsImpl usersDetails) {
        return usersDetails.getUsername();
    }

}
