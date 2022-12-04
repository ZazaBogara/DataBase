package com.mosorin.lab5.controller;

import com.mosorin.lab5.domain.UserEntity;
import com.mosorin.lab5.dto.UserDto;
import com.mosorin.lab5.dto.assembler.UserDtoAssembler;
import com.mosorin.lab5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserDtoAssembler userDtoAssembler;

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer userId) {
        UserEntity user = userService.findById(userId);
        UserDto userDto = userDtoAssembler.toModel(user);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<UserDto>> getAllUsers() {
        List<UserEntity> users = userService.findAll();
        CollectionModel<UserDto> userDtos = userDtoAssembler.toCollectionModel(users);
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto dto) {
        UserEntity newEntity = new UserEntity();
        newEntity.setName(dto.getName());
        UserEntity newUser = userService.create(newEntity);
        UserDto userDto = userDtoAssembler.toModel(newUser);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{userId}")
    public ResponseEntity<?> updateUser(@RequestBody UserEntity uUser, @PathVariable Integer userId) {
        userService.update(userId, uUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId) {
        userService.delete(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
