package com.mosorin.controller;

import com.mosorin.domain.Server;
import com.mosorin.service.GeneralService;

import java.util.Optional;

public interface ServerController extends GeneralController<Server, Integer> {
    public Optional<Server> findByName(String name);
}
