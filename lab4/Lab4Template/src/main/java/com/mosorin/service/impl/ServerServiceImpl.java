package com.mosorin.service.impl;

import com.mosorin.dao.ServerDAO;
import com.mosorin.domain.Server;
import com.mosorin.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServerServiceImpl implements ServerService {
    @Autowired
    private ServerDAO serverDao;

    @Override
    public List<Server> findAll() {
        return serverDao.findAll();
    }

    @Override
    public Optional<Server> findById(Integer id) {
        return serverDao.findById(id);
    }

    @Override
    public int create(Server server) {
        return serverDao.create(server);
    }

    @Override
    public int update(Integer id, Server server) {
        return serverDao.update(id, server);
    }

    @Override
    public int delete(Integer id) {
        return serverDao.delete(id);
    }

    @Override
    public Optional<Server> findByName(String name) {
        return serverDao.findByName(name);
    }
}
