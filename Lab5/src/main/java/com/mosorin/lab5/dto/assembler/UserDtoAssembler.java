package com.mosorin.lab5.dto.assembler;

import com.mosorin.lab5.controller.UserController;
import com.mosorin.lab5.domain.UserEntity;
import com.mosorin.lab5.dto.UserDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
@Component
public class UserDtoAssembler implements RepresentationModelAssembler<UserEntity, UserDto> {
    @Override
    public UserDto toModel(UserEntity user){
        UserDto userDto = UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
        Link selfLink = linkTo(methodOn(UserController.class).getUser(userDto.getId())).withSelfRel();
        userDto.add(selfLink);
        return userDto;
    }
    @Override
    public CollectionModel<UserDto> toCollectionModel(Iterable<? extends UserEntity> entities) {
        CollectionModel<UserDto> userDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(UserController.class).getAllUsers()).withSelfRel();
        userDtos.add(selfLink);
        return userDtos;
    }
}
