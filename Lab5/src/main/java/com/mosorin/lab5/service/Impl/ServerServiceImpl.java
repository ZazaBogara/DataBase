package com.mosorin.lab5.service.Impl;

import com.mosorin.lab5.domain.ServerEntity;
import com.mosorin.lab5.repository.ServerRepository;
import com.mosorin.lab5.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ServerServiceImpl implements ServerService {
    @Autowired
    ServerRepository serverRepository;

    public List<ServerEntity> findAll() {
        return serverRepository.findAll();
    }

    public ServerEntity findById(Integer id) {
        return serverRepository.findById(id).orElseThrow();
    }

    @Transactional
    public ServerEntity create(ServerEntity entity) {
        return null;
    }

    @Transactional
    public void update(Integer id, ServerEntity entity) {
        ServerEntity toChangeEntity = serverRepository.findById(id).orElseThrow();
        toChangeEntity.setName(entity.getName());
        toChangeEntity.setChats(entity.getChats());
    }

    @Transactional
    public void delete(Integer id) {
        ServerEntity toDeleteEntity = serverRepository.findById(id).orElseThrow();
        serverRepository.delete(toDeleteEntity);
    }

    public List<ServerEntity> findAllByName(String name) {
        return serverRepository.findAllByName(name);
    }

    @Transactional
    public ServerEntity insertNewServer(String name) {
        serverRepository.insertNewServer(name);
        ServerEntity toInsert = new ServerEntity();
        toInsert.setName(name);
        toInsert.setId(serverRepository.findAllByName(name).get(0).getId());
        return toInsert;
    }
}
