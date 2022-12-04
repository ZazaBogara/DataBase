package com.mosorin.service.impl;

import com.mosorin.controller.ChatController;
import com.mosorin.dao.ChatDAO;
import com.mosorin.domain.Chat;
import com.mosorin.domain.User;
import com.mosorin.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatDAO chatDao;

    @Override
    public List<Chat> findAll() {
        return chatDao.findAll();
    }

    @Override
    public Optional<Chat> findById(Integer id) {
        return chatDao.findById(id);
    }

    @Override
    public int create(Chat chat) {
        return chatDao.create(chat);
    }

    @Override
    public int update(Integer id, Chat chat) {
        return chatDao.update(id, chat);
    }

    @Override
    public int delete(Integer id) {
        return chatDao.delete(id);
    }

    @Override
    public Optional<Chat> findByName(String name) {
        return chatDao.findByName(name);
    }

    @Override
    public List<User> findRelatedUsers(Integer id) {
        return chatDao.findRelatedUsers(id);
    }
}
