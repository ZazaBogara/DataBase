package com.mosorin.lab5.service.Impl;

import com.mosorin.lab5.domain.MessageEntity;
import com.mosorin.lab5.repository.MessageRepository;
import com.mosorin.lab5.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageRepository messageRepository;

    public List<MessageEntity> findAll() {
        return messageRepository.findAll();
    }

    public MessageEntity findById(Integer id) {
        return messageRepository.findById(id).orElseThrow();
    }

    @Transactional
    public MessageEntity create(MessageEntity entity) {
        messageRepository.save(entity);
        return entity;
    }

    @Transactional
    public void update(Integer id, MessageEntity entity) {
        MessageEntity toChangeEntity = messageRepository.findById(id).orElseThrow();
        toChangeEntity.setAudio(entity.getAudio());
        toChangeEntity.setPhoto(entity.getPhoto());
        toChangeEntity.setChat(entity.getChat());
        toChangeEntity.setText(entity.getText());
        toChangeEntity.setTime(entity.getTime());
        toChangeEntity.setUser(entity.getUser());
        messageRepository.save(toChangeEntity);
    }

    @Transactional
    public void delete(Integer id) {
        MessageEntity toDeleteEntity =messageRepository.findById(id).orElseThrow();
        messageRepository.delete(toDeleteEntity);
    }

    public MessageEntity findByText(String text) {
        return messageRepository.findByText(text);
    }
}
