package com.mosorin.lab5.controller;

import com.mosorin.lab5.domain.ChatEntity;
import com.mosorin.lab5.domain.ServerEntity;
import com.mosorin.lab5.dto.ChatDto;
import com.mosorin.lab5.dto.ServerDto;
import com.mosorin.lab5.dto.assembler.ChatDtoAssembler;
import com.mosorin.lab5.dto.assembler.ServerDtoAssembler;
import com.mosorin.lab5.service.ChatService;
import com.mosorin.lab5.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/servers")
public class ServerController {
    @Autowired
    private ServerService serverService;

    @Autowired
    private ServerDtoAssembler serverDtoAssembler;

    @GetMapping(value = "/{serverId}")
    public ResponseEntity<ServerDto> getServer(@PathVariable Integer serverId) {
        ServerEntity server = serverService.findById(serverId);
        ServerDto serverDto = serverDtoAssembler.toModel(server);
        return new ResponseEntity<>(serverDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<ServerDto>> getAllChats() {
        List<ServerEntity> chats = serverService.findAll();
        CollectionModel<ServerDto> serverDtos = serverDtoAssembler.toCollectionModel(chats);
        return new ResponseEntity<>(serverDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<ServerDto> addServer(@RequestBody ServerDto dto) {
        ServerEntity entity = new ServerEntity();
        entity.setName(dto.getName());
        ServerEntity newServer = serverService.create(entity);
        ServerDto serverDto = serverDtoAssembler.toModel(newServer);
        return new ResponseEntity<>(serverDto, HttpStatus.CREATED);
    }

    @PostMapping(value = "/procedure")
    public ResponseEntity<ServerDto> insertNewServer(@RequestBody ServerDto dto) {
        ServerEntity newServer = serverService.insertNewServer(dto.getName());
        ServerDto serverDto = serverDtoAssembler.toModel(newServer);
        return new ResponseEntity<>(serverDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{serverId}")
    public ResponseEntity<?> updateServer(@RequestBody ServerEntity uServer, @PathVariable Integer serverId) {
        serverService.update(serverId, uServer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{serverId}")
    public ResponseEntity<?> deleteServer(@PathVariable Integer serverId) {
        serverService.delete(serverId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
