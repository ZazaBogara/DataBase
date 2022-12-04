package com.mosorin.lab5.repository;

import com.mosorin.lab5.domain.ServerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServerRepository extends JpaRepository<ServerEntity, Integer> {
    List<ServerEntity> findAllByName(String name);

    @Procedure("insertNewServer")
    void insertNewServer(String name);
}
