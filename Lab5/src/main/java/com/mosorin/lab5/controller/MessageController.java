package com.mosorin.lab5.controller;

import com.mosorin.lab5.domain.ChatEntity;
import com.mosorin.lab5.domain.MessageEntity;
import com.mosorin.lab5.dto.ChatDto;
import com.mosorin.lab5.dto.MessageDto;
import com.mosorin.lab5.dto.assembler.ChatDtoAssembler;
import com.mosorin.lab5.dto.assembler.MessageDtoAssembler;
import com.mosorin.lab5.service.ChatService;
import com.mosorin.lab5.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageDtoAssembler messageDtoAssembler;

    @GetMapping(value = "/{messageId}")
    public ResponseEntity<MessageDto> getMessage(@PathVariable Integer messageId) {
        MessageEntity message = messageService.findById(messageId);
        MessageDto messageDto = messageDtoAssembler.toModel(message);
        return new ResponseEntity<>(messageDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<MessageDto>> getAllMessages() {
        List<MessageEntity> messages = messageService.findAll();
        CollectionModel<MessageDto> messageDtos = messageDtoAssembler.toCollectionModel(messages);
        return new ResponseEntity<>(messageDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<MessageDto> addMessage(@RequestBody MessageEntity entity) {
        MessageEntity newMessage = messageService.create(entity);
        MessageDto messageDto = messageDtoAssembler.toModel(newMessage);
        return new ResponseEntity<>(messageDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{messageId}")
    public ResponseEntity<?> updateMessage(@RequestBody MessageEntity uMessage, @PathVariable Integer messageId) {
        messageService.update(messageId, uMessage);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{messageId}")
    public ResponseEntity<?> deleteMessage(@PathVariable Integer messageId) {
        messageService.delete(messageId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
