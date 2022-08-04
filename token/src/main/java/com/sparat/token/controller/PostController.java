package com.sparat.token.controller;

import com.sparat.token.domains.auth.domain.UserDetailsImpl;
import com.sparat.token.dto.PostRequestDto;
import com.sparat.token.dto.ResponseDto;
import com.sparat.token.dto.passwordDto;
import com.sparat.token.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @GetMapping("/api/post/{id}")
    public ResponseDto<?> getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @GetMapping("/api/post")
    public ResponseDto<?> getAllPosts() {
        return postService.getAllPost();
    }

    @PostMapping("/api/post")
    public ResponseDto<?> createPost(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                     @RequestBody PostRequestDto requestDto) {
        return postService.createPost(userDetails, requestDto);
    }

    @PutMapping("/api/post/{id}")
    public ResponseDto<?> updatePost(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                     @PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {
        return postService.updatePost(userDetails, id, postRequestDto);
    }

    @DeleteMapping("/api/post/{id}")
    public ResponseDto<?> deletePost(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long id) {

        return postService.deletePost(userDetails, id);
    }

    @PostMapping("/api/post/{id}")
    public ResponseDto<?> validateAuthorByPassword(
            @PathVariable Long id,
            @RequestBody passwordDto password) {
        return postService.validateAuthorByPassword(id, password);
    }
}
