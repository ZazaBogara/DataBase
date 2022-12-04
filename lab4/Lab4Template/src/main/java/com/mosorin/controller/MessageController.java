package com.mosorin.controller;

import com.mosorin.domain.Message;

import java.util.Optional;

public interface MessageController extends GeneralController<Message, Integer>{
    public Optional<Message> findByText(String text);
}
