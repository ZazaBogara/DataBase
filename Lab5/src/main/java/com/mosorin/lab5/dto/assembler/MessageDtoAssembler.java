package com.mosorin.lab5.dto.assembler;

import com.mosorin.lab5.controller.ChatController;
import com.mosorin.lab5.controller.MessageController;
import com.mosorin.lab5.domain.ChatEntity;
import com.mosorin.lab5.domain.MessageEntity;
import com.mosorin.lab5.dto.ChatDto;
import com.mosorin.lab5.dto.MessageDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class MessageDtoAssembler implements RepresentationModelAssembler<MessageEntity, MessageDto> {
    @Override
    public MessageDto toModel(MessageEntity message){
        MessageDto messageDto = MessageDto.builder()
                .id(message.getId())
                .text(message.getText())
                .audio(message.getAudio())
                .photo(message.getPhoto())
                .chat_id(message.getChat().getId())
                .user_id(message.getUser().getId())
                .build();
        Link selfLink = linkTo(methodOn(MessageController.class).getMessage(messageDto.getId())).withSelfRel();
        messageDto.add(selfLink);
        return messageDto;
    }
    @Override
    public CollectionModel<MessageDto> toCollectionModel(Iterable<? extends MessageEntity> entities) {
        CollectionModel<MessageDto> messageDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(MessageController.class).getAllMessages()).withSelfRel();
        messageDtos.add(selfLink);
        return messageDtos;
    }
}
