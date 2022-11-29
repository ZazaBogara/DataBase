package com.mosorin.controller;

import com.mosorin.domain.Chat;
import com.mosorin.domain.User;
import com.mosorin.service.GeneralService;

import java.util.List;
import java.util.Optional;

public interface UserController extends GeneralController<User, Integer> {
    public Optional<User> findByName(String name);

    public List<Chat> findRelatedChats(Integer id);
}

