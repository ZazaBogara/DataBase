package com.mosorin.dao;

import com.mosorin.domain.Message;

import java.util.Optional;

public interface MessageDAO extends GeneralDao<Message, Integer>{
    public Optional<Message> findByText(String text);
}
