package com.mosorin.service;

import com.mosorin.domain.Chat;
import com.mosorin.domain.User;

import java.util.List;
import java.util.Optional;

public interface ChatService extends GeneralService<Chat, Integer>{

    public Optional<Chat> findByName(String name);

    public List<User> findRelatedUsers(Integer id);
}
