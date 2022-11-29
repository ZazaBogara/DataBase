package com.mosorin.service;

import com.mosorin.domain.Server;

import java.util.Optional;

public interface ServerService extends GeneralService<Server, Integer>{
    public Optional<Server> findByName(String name);
}
