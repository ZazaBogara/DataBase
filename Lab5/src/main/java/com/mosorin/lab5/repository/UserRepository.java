package com.mosorin.lab5.repository;

import com.mosorin.lab5.domain.ServerEntity;
import com.mosorin.lab5.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    List<UserEntity> findAllByName(String name);
}
