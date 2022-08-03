package com.sparta.token.controller;

import com.sparta.token.model.PasswordDto;
import com.sparta.token.model.Post;
import com.sparta.token.model.PostRequestDto;
import com.sparta.token.repository.PostRepository;
import com.sparta.token.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;

    // 모든 데이터 전달
    @GetMapping("/api/post")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/api/post/{id}")
    public Post getPost(@PathVariable Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new NullPointerException("데이터가 없습니다.")
        );

        return post;
    }

    @PostMapping("/api/post")
    public Long createPost(@RequestBody PostRequestDto requestDto) {
        return postService.createPost(requestDto).getId();
    }

    @PostMapping("/api/post/{id}")
    public boolean checkPw(@PathVariable Long id, @RequestBody PasswordDto passwordDto) {
        if(postService.checkPw(id, passwordDto))
            return true;
        else
            return false;
    }

    @PutMapping("/api/post/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        Post post = postService.update(id, requestDto);
        return post;
    }

    @DeleteMapping("/api/post/{id}")
    public Long deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
        return id;
    }
}
