package com.example.demo.repository;

import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaUserRepository extends JpaRepository<UserEntity, Integer>, UserRepository {
}
