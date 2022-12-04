package com.mosorin.lab5.service;

import com.mosorin.lab5.domain.MessageEntity;

public interface MessageService extends GeneralService<MessageEntity, Integer>{
    MessageEntity findByText(String text);
}
