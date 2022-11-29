package com.mosorin.service;

import com.mosorin.domain.Message;

import java.util.Optional;

public interface MessageService extends GeneralService<Message, Integer>{
    public Optional<Message> findByText(String text);
}
