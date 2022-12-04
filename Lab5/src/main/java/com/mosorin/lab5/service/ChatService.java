package com.mosorin.lab5.service;

import com.mosorin.lab5.domain.ChatEntity;

import java.util.List;

public interface ChatService extends GeneralService<ChatEntity, Integer>{
    List<ChatEntity> findAllByName(String name);

}
