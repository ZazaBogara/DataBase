package com.mosorin.dao.impl;

import com.mosorin.dao.ServerDAO;
import com.mosorin.domain.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class ServerDaoImpl implements ServerDAO {
    private static final String FIND_ALL = "SELECT * FROM server";
    private static final String CREATE = "INSERT server(name) VALUES (?)";
    private static final String UPDATE = "UPDATE server SET name = ? WHERE id=?";
    private static final String DELETE = "DELETE FROM server WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM server WHERE id=?";
    private static final String FIND_BY_NAME = "SELECT * FROM server WHERE name=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Server> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Server.class));
    }

    @Override
    public Optional<Server> findById(Integer id) {
        Optional<Server> server;
        try {
            server = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Server.class), id));
        } catch (EmptyResultDataAccessException e) {
            server = Optional.empty();
        }
        return server;
    }

    @Override
    public int create(Server server) {
        return jdbcTemplate.update(CREATE, server.getName());
    }

    @Override
    public int update(Integer id, Server server) {
        return jdbcTemplate.update(UPDATE, server.getName(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }

    @Override
    public Optional<Server> findByName(String name) {
        Optional<Server> server;
        try {
            server = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_NAME,
                    BeanPropertyRowMapper.newInstance(Server.class), name));
        } catch (EmptyResultDataAccessException e) {
            server = Optional.empty();
        }
        return server;
    }
}
