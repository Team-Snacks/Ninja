package com.example.demo.repository;

import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository{
    List<UserEntity> findAll();

    UserEntity save(UserEntity userEntity);

    @Query(value = "SELECT * FROM USER_ENTITY WHERE EMAIL = ?1", nativeQuery = true)
    UserEntity findByName(String value);

    void deleteById(int id);
}
