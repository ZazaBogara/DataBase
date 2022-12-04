package com.mosorin.lab5.service;

import com.mosorin.lab5.domain.UserEntity;

import java.util.List;

public interface UserService extends GeneralService<UserEntity, Integer>{
    List<UserEntity> findAllByName(String name);
}
