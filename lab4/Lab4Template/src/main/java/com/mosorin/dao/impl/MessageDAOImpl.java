package com.mosorin.dao.impl;


import com.mosorin.dao.MessageDAO;
import com.mosorin.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class MessageDAOImpl implements MessageDAO {
    private static final String FIND_ALL = "SELECT * FROM message";
    private static final String CREATE = "INSERT message(text, chat_id, user_id, time) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE message SET text=?, chat_id=?, user_id=?, time=? WHERE id=?";
    private static final String DELETE = "DELETE FROM message WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM message WHERE id=?";
    private static final String FIND_BY_TEXT = "SELECT * FROM message WHERE text=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Message> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Message.class));
    }

    @Override
    public Optional<Message> findById(Integer id) {
        Optional<Message> message;
        try {
            message = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Message.class), id));
        } catch (EmptyResultDataAccessException e) {
            message = Optional.empty();
        }
        return message;
    }

    @Override
    public int create(Message message) {
        return jdbcTemplate.update(CREATE, message.getText(), message.getChat_id(), message.getUser_id(), message.getTime());
    }

    @Override
    public int update(Integer id, Message message) {
        return jdbcTemplate.update(UPDATE, message.getText(), message.getChat_id(), message.getUser_id(), message.getTime(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }

    @Override
    public Optional<Message> findByText(String text) {
        Optional<Message> message;
        try {
            message = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_TEXT,
                    BeanPropertyRowMapper.newInstance(Message.class), text));
        } catch (EmptyResultDataAccessException e) {
            message = Optional.empty();
        }
        return message;
    }
}
