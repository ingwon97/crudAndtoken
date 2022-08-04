package com.sparat.token.service;

import com.sparat.token.dto.UserRequest;
import com.sparat.token.dto.UsersDetailsImpl;
import com.sparat.token.model.Post;
import com.sparat.token.model.PostRequestDto;
import com.sparat.token.model.UsersEntity;
import com.sparat.token.repository.PostRepository;
import com.sparat.token.repository.UsersRepository;
import com.sparat.token.dto.ResponseDto;
import com.sparat.token.dto.passwordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UsersRepository usersRepository;

    @Transactional
    public ResponseDto<?> createPost(UsersDetailsImpl usersDetails, PostRequestDto requestDto) {

        Post post = new Post(usersDetails, requestDto);

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
    public ResponseDto<Post> updatePost(UsersDetailsImpl usersDetails, Long id, PostRequestDto requestDto) {
        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isEmpty()) {
            return ResponseDto.fail("NULL_POST_ID", "post id isn't exist");
        }

        Post post = optionalPost.get();
        post.update(usersDetails, requestDto);

        return ResponseDto.success(post);
    }

    @Transactional
    public ResponseDto<?> deletePost(UsersDetailsImpl userDetails, Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isEmpty()) {
            return ResponseDto.fail("NOT_FOUND", "post id is not exist");
        }

        Post post = optionalPost.get();

        postRepository.delete(post);

        return ResponseDto.success(true);
    }

    // 여기는 비밀번호 검증을 위해서 UsersEntity로 받음
    @Transactional(readOnly = true)
    public ResponseDto<?> validateAuthorByPassword(UsersDetailsImpl userDetails, Long id) {
        Optional<UsersEntity> optionalPost = usersRepository.findById(id);

        if (optionalPost.isEmpty()) {
            return ResponseDto.fail("NOT_FOUND", "post id is not exist");
        }

        UsersEntity usersEntity = optionalPost.get();

        if (!usersEntity.getPassword().equals(userDetails.getPassword())) {
            return ResponseDto.fail("PASSWORD_NOT_CORRECT", "password is not correct");
        }

        return ResponseDto.success(true);
    }


}