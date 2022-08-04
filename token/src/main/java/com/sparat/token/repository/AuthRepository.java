package com.sparat.token.repository;

import com.sparat.token.model.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<AuthEntity, Long> {

  Optional<AuthEntity> findByUsersEntityId(Long userId);
}
