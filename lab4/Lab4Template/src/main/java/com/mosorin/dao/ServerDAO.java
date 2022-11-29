package com.mosorin.dao;

import com.mosorin.domain.Server;

import java.util.Optional;

public interface ServerDAO extends GeneralDao<Server, Integer> {
    public Optional<Server> findByName(String name);
}
