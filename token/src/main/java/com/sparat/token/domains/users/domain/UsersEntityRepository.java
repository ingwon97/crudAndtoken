package com.sparat.token.domains.users.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersEntityRepository extends JpaRepository<UsersEntity,Long> {
    Optional<UsersEntity> findByUserId(String userId);
    Optional<UsersEntity> findByUsername(String username);
}
