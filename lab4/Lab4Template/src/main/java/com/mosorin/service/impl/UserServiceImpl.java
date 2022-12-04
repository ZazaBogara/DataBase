package com.mosorin.service.impl;

import com.mosorin.dao.UserDAO;
import com.mosorin.domain.Chat;
import com.mosorin.domain.User;
import com.mosorin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public int create(User user) {
        return userDao.create(user);
    }

    @Override
    public int update(Integer id, User user) {
        return userDao.update(id, user);
    }

    @Override
    public int delete(Integer id) {
        return userDao.delete(id);
    }

    @Override
    public Optional<User> findByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public List<Chat> findRelatedChats(Integer id) {
        return userDao.findRelatedChats(id);
    }
}
