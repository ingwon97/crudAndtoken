package com.sparta.token.service;

import com.sparta.token.model.PasswordDto;
import com.sparta.token.model.Post;
import com.sparta.token.model.PostRequestDto;
import com.sparta.token.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Post update(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new NullPointerException("데이터가 없습니다")
        );
        post.update(requestDto);
        return post;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post createPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        return postRepository.save(post);
    }

    public boolean checkPw(Long id, PasswordDto passwordDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new NullPointerException("데이터가 없습니다")
        );
        if (post.getPassword().equals(passwordDto.getPassword()))
            return true;
        else
            return false;
    }


}
