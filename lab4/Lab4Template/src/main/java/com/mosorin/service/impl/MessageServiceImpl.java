package com.mosorin.service.impl;

import com.mosorin.dao.MessageDAO;
import com.mosorin.domain.Message;
import com.mosorin.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDAO messageDao;

    @Override
    public List<Message> findAll() {
        return messageDao.findAll();
    }

    @Override
    public Optional<Message> findById(Integer id) {
        return messageDao.findById(id);
    }

    @Override
    public int create(Message message) {
        return messageDao.create(message);
    }

    @Override
    public int update(Integer id, Message message) {
        return messageDao.update(id, message);
    }

    @Override
    public int delete(Integer id) {
        return messageDao.delete(id);
    }

    public Optional<Message> findByText(String text){
        return messageDao.findByText(text);
    }
}
