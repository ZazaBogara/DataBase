package com.mosorin.lab5.controller;

import com.mosorin.lab5.domain.ChatEntity;
import com.mosorin.lab5.dto.ChatDto;
import com.mosorin.lab5.dto.assembler.ChatDtoAssembler;
import com.mosorin.lab5.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/chats")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatDtoAssembler chatDtoAssembler;

    @GetMapping(value = "/{chatId}")
    public ResponseEntity<ChatDto> getChat(@PathVariable Integer chatId) {
        ChatEntity chat = chatService.findById(chatId);
        ChatDto chatDto = chatDtoAssembler.toModel(chat);
        return new ResponseEntity<>(chatDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<ChatDto>> getAllChats() {
        List<ChatEntity> chats = chatService.findAll();
        CollectionModel<ChatDto> chatDtos = chatDtoAssembler.toCollectionModel(chats);
        return new ResponseEntity<>(chatDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<ChatDto> addChat(@RequestBody ChatEntity entity) {
        ChatEntity newChat = chatService.create(entity);
        ChatDto chatDto = chatDtoAssembler.toModel(newChat);
        return new ResponseEntity<>(chatDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{chatId}")
    public ResponseEntity<?> updateChat(@RequestBody ChatEntity uChat, @PathVariable Integer chatId) {
        chatService.update(chatId, uChat);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{chatId}")
    public ResponseEntity<?> deleteChat(@PathVariable Integer chatId) {
        chatService.delete(chatId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
