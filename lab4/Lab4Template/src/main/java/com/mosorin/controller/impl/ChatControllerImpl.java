package com.mosorin.controller.impl;

import com.mosorin.controller.ChatController;
import com.mosorin.domain.Chat;
import com.mosorin.domain.User;
import com.mosorin.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatControllerImpl implements ChatController {
    @Autowired
    private ChatService chatService;

    @Override
    public List<Chat> findAll() {
        return chatService.findAll();
    }

    @Override
    public Optional<Chat> findById(Integer id) {
        return chatService.findById(id);
    }

    @Override
    public int create(Chat chat) {
        return chatService.create(chat);
    }

    @Override
    public int update(Integer id, Chat chat) {
        return chatService.update(id, chat);
    }

    @Override
    public int delete(Integer id) {
        return chatService.delete(id);
    }

    @Override
    public Optional<Chat> findByName(String name) {
        return chatService.findByName(name);
    }

    @Override
    public List<User> findRelatedUsers(Integer id) {
        return chatService.findRelatedUsers(id);
    }
}
