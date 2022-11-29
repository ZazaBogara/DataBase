package com.mosorin.lab5.dto.assembler;

import com.mosorin.lab5.controller.ChatController;
import com.mosorin.lab5.domain.ChatEntity;
import com.mosorin.lab5.dto.ChatDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ChatDtoAssembler implements RepresentationModelAssembler<ChatEntity, ChatDto> {
    @Override
    public ChatDto toModel(ChatEntity chat){
        ChatDto chatDto = ChatDto.builder()
                .id(chat.getId())
                .name(chat.getName())
                .build();
        Link selfLink = linkTo(methodOn(ChatController.class).getChat(chatDto.getId())).withSelfRel();
        chatDto.add(selfLink);
        return chatDto;
    }
    @Override
    public CollectionModel<ChatDto> toCollectionModel(Iterable<? extends ChatEntity> entities) {
        CollectionModel<ChatDto> chatDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(ChatController.class).getAllChats()).withSelfRel();
        chatDtos.add(selfLink);
        return chatDtos;
    }
}
