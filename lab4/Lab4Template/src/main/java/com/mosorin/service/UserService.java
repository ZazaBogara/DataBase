package com.mosorin.service;

import com.mosorin.domain.Chat;
import com.mosorin.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends GeneralService<User, Integer>{
    public Optional<User> findByName(String name);

    public List<Chat> findRelatedChats(Integer id);
}

