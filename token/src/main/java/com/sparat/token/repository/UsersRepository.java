package com.sparat.token.repository;

import com.sparat.token.model.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<UsersEntity,Long> {
    Optional<UsersEntity> findByAuthor(String userId);
    List<UsersEntity> findAllByOrderByModifiedAtDesc();

}
