package com.mosorin.lab5.dto.assembler;

import com.mosorin.lab5.controller.ChatController;
import com.mosorin.lab5.controller.ServerController;
import com.mosorin.lab5.domain.ChatEntity;
import com.mosorin.lab5.domain.ServerEntity;
import com.mosorin.lab5.dto.ChatDto;
import com.mosorin.lab5.dto.ServerDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ServerDtoAssembler implements RepresentationModelAssembler<ServerEntity, ServerDto> {
    @Override
    public ServerDto toModel(ServerEntity server){
        ServerDto serverDto = ServerDto.builder()
                .id(server.getId())
                .name(server.getName())
                .build();
        Link selfLink = linkTo(methodOn(ServerController.class).getServer(serverDto.getId())).withSelfRel();
        serverDto.add(selfLink);
        return serverDto;
    }
    @Override
    public CollectionModel<ServerDto> toCollectionModel(Iterable<? extends ServerEntity> entities) {
        CollectionModel<ServerDto> serverDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(ServerController.class).getAllChats()).withSelfRel();
        serverDtos.add(selfLink);
        return serverDtos;
    }
}
