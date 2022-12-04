package com.mosorin.controller.impl;

import com.mosorin.controller.ServerController;
import com.mosorin.domain.Server;
import com.mosorin.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServerControllerImpl implements ServerController {
    @Autowired
    private ServerService serverService;

    @Override
    public List<Server> findAll() {
        return serverService.findAll();
    }

    @Override
    public Optional<Server> findById(Integer id) {
        return serverService.findById(id);
    }

    @Override
    public int create(Server server) {
        return serverService.create(server);
    }

    @Override
    public int update(Integer id, Server server) {
        return serverService.update(id, server);
    }

    @Override
    public int delete(Integer id) {
        return serverService.delete(id);
    }

    @Override
    public Optional<Server> findByName(String name) {
        return serverService.findByName(name);
    }
}
