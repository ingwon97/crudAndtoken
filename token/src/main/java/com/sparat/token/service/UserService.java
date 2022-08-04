package com.sparat.token.service;

import com.sparat.token.commons.Jwt.TokenUtils;
import com.sparat.token.model.AuthEntity;
import com.sparat.token.repository.AuthRepository;
import com.sparat.token.dto.TokenResponse;
import com.sparat.token.dto.UserRequest;
import com.sparat.token.model.UsersEntity;
import com.sparat.token.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UsersRepository usersRepository;
    private final TokenUtils tokenUtils;
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<UsersEntity> findByAuthor(String author) {

        return usersRepository.findByAuthor(author);
    }

    @Transactional
    public TokenResponse signUp(UserRequest userRequest) {
        UsersEntity usersEntity =
                usersRepository.save(
                        UsersEntity.builder()
                                .password(passwordEncoder.encode(userRequest.getPassword()))
                                .author(userRequest.getAuthor())
                                .build());

        String accessToken = tokenUtils.generateJwtToken(usersEntity);
        String refreshToken = tokenUtils.saveRefreshToken(usersEntity);

        authRepository.save(
                AuthEntity.builder().usersEntity(usersEntity).refreshToken(refreshToken).build());

        return TokenResponse.builder().ACCESS_TOKEN(accessToken).REFRESH_TOKEN(refreshToken).build();
    }

    @Transactional
    public TokenResponse signIn(UserRequest userRequest) throws Exception {
        UsersEntity usersEntity =
                usersRepository
                        .findByAuthor(userRequest.getAuthor())
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        AuthEntity authEntity =
                authRepository
                        .findByUsersEntityId(usersEntity.getId())
                        .orElseThrow(() -> new IllegalArgumentException("Token 이 존재하지 않습니다."));
        if (!passwordEncoder.matches(userRequest.getPassword(), usersEntity.getPassword())) {
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }
        String accessToken = "";
        String refreshToken = authEntity.getRefreshToken();

        if (tokenUtils.isValidRefreshToken(refreshToken)) {
            accessToken = tokenUtils.generateJwtToken(authEntity.getUsersEntity());
            return TokenResponse.builder()
                    .ACCESS_TOKEN(accessToken)
                    .REFRESH_TOKEN(authEntity.getRefreshToken())
                    .build();
        } else {
            accessToken = tokenUtils.generateJwtToken(authEntity.getUsersEntity());
            refreshToken = tokenUtils.saveRefreshToken(usersEntity);
            authEntity.refreshUpdate(refreshToken);
        }

        return TokenResponse.builder().ACCESS_TOKEN(accessToken).REFRESH_TOKEN(refreshToken).build();
    }

    public List<UsersEntity> findUsers() {
        return usersRepository.findAll();
    }
}
