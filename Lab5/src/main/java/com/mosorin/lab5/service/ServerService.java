package com.mosorin.lab5.service;

import com.mosorin.lab5.domain.ServerEntity;

import java.util.List;

public interface ServerService extends GeneralService<ServerEntity, Integer>{
    List<ServerEntity> findAllByName(String name);

    ServerEntity insertNewServer(String name);
}
