package com.mosorin.controller.impl;

import com.mosorin.controller.MessageController;
import com.mosorin.domain.Message;
import com.mosorin.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageControllerImpl implements MessageController {
    @Autowired
    private MessageService messageService;

    @Override
    public List<Message> findAll() {
        return messageService.findAll();
    }

    @Override
    public Optional<Message> findById(Integer id) {
        return messageService.findById(id);
    }

    @Override
    public int create(Message message) {
        return messageService.create(message);
    }

    @Override
    public int update(Integer id, Message message) {
        return messageService.update(id, message);
    }

    @Override
    public int delete(Integer id) {
        return messageService.delete(id);
    }

    public Optional<Message> findByText(String text){
        return messageService.findByText(text);
    }
}
