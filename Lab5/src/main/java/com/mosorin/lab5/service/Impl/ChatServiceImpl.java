package com.mosorin.lab5.service.Impl;

import com.mosorin.lab5.domain.ChatEntity;
import com.mosorin.lab5.repository.ChatRepository;
import com.mosorin.lab5.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    ChatRepository chatRepository;

    public List<ChatEntity> findAllByName(String name) {
        return chatRepository.findAllByName(name);
    }

    public List<ChatEntity> findAll() {
        return chatRepository.findAll();
    }

    public ChatEntity findById(Integer id) {
        return chatRepository.findById(id).orElseThrow();
    }

    @Transactional
    public ChatEntity create(ChatEntity entity) {
        chatRepository.save(entity);
        return entity;
    }

    @Transactional
    public void update(Integer id, ChatEntity entity) {
        ChatEntity toChangeEntity =chatRepository.findById(id).orElseThrow();
        toChangeEntity.setName(entity.getName());
        toChangeEntity.setMessages(entity.getMessages());
        toChangeEntity.setServer(entity.getServer());
        toChangeEntity.setUsers(entity.getUsers());
        chatRepository.save(toChangeEntity);
    }

    @Transactional
    public void delete(Integer id) {
        ChatEntity toDeleteEntity = chatRepository.findById(id)
                .orElseThrow();
        chatRepository.delete(toDeleteEntity);
    }
}
