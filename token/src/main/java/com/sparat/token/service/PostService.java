package com.sparat.token.service;

import com.sparat.token.domains.auth.domain.UserDetailsImpl;
import com.sparat.token.dto.PostRequestDto;
import com.sparat.token.dto.ResponseDto;
import com.sparat.token.dto.passwordDto;
import com.sparat.token.model.Post;
import com.sparat.token.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public ResponseDto<?> createPost(UserDetailsImpl userDetails, PostRequestDto requestDto) {

        Post post = new Post(userDetails, requestDto);
        postRepository.save(post);

        return ResponseDto.success(post);
    }

    @Transactional(readOnly = true)
    public ResponseDto<?> getPost(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isEmpty()) {
            return ResponseDto.fail("NULL_POST_ID", "post id isn't exist");
        }

        return ResponseDto.success(optionalPost.get());
    }

    @Transactional(readOnly = true)
    public ResponseDto<?> getAllPost() {
        return ResponseDto.success(postRepository.findAllByOrderByModifiedAtDesc());
    }

    @Transactional
    public ResponseDto<Post> updatePost(UserDetailsImpl userDetails, Long id, PostRequestDto requestDto) {
        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isEmpty()) {
            return ResponseDto.fail("NULL_POST_ID", "post id isn't exist");
        }

        Post post = optionalPost.get();

        if (!userDetails.getUsername().equals(post.getAuthor()))
            return ResponseDto.fail("NOT_FOUND", "post 주인이 아님");

        post.update(requestDto);
        return ResponseDto.success(post);
    }

    @Transactional
    public ResponseDto<?> deletePost(UserDetailsImpl userDetails, Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isEmpty()) {
            return ResponseDto.fail("NOT_FOUND", "post id is not exist");
        }

        Post post = optionalPost.get();

        if (!userDetails.getUser().getNickname().equals(post.getAuthor())) {
            return ResponseDto.fail("NOT_FOUND", "post 주인이 아님");
        }

        postRepository.delete(post);
        return ResponseDto.success(true);
    }

    @Transactional(readOnly = true)
    public ResponseDto<?> validateAuthorByPassword(Long id, passwordDto password) {
        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isEmpty()) {
            return ResponseDto.fail("NOT_FOUND", "post id is not exist");
        }

        Post post = optionalPost.get();

        if (!post.getPassword().equals(password.getPassword())) {
            return ResponseDto.fail("PASSWORD_NOT_CORRECT", "password is not correct");
        }

        return ResponseDto.success(true);
    }


}