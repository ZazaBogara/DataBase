package com.mosorin.lab5.service.Impl;

import com.mosorin.lab5.domain.UserEntity;
import com.mosorin.lab5.repository.UserRepository;
import com.mosorin.lab5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public UserEntity findById(Integer id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Transactional
    public UserEntity create(UserEntity entity) {
        userRepository.save(entity);
        return entity;
    }

    @Transactional
    public void update(Integer id, UserEntity entity) {
        UserEntity toChangeEntity = userRepository.findById(id).orElseThrow();
        toChangeEntity.setMessages(entity.getMessages());
        toChangeEntity.setChats(entity.getChats());
        toChangeEntity.setName(entity.getName());
        userRepository.save(toChangeEntity);
    }

    @Transactional
    public void delete(Integer id) {
        UserEntity toDeleteEntity = userRepository.findById(id).orElseThrow();
        userRepository.delete(toDeleteEntity);
    }

    public List<UserEntity> findAllByName(String name) {
        return userRepository.findAllByName(name);
    }
}
