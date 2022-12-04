package com.mosorin.dao.impl;

import com.mosorin.dao.UserDAO;
import com.mosorin.domain.Chat;
import com.mosorin.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class UserDAOImpl implements UserDAO {
    private static final String FIND_ALL = "SELECT * FROM user";
    private static final String CREATE = "INSERT user(name) VALUES (?)";
    private static final String UPDATE = "UPDATE user SET name = ? WHERE id=?";
    private static final String DELETE = "DELETE FROM user WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM user WHERE id=?";
    private static final String FIND_BY_NAME = "SELECT * FROM user WHERE name=?";

    private static final String FIND_RELATED_CHATS = "SELECT chat.id, chat.name, server_id FROM user " +
            "RIGHT JOIN chat_has_user ON user.id = chat_has_user.user_id " +
            "JOIN chat ON chat.id = chat_has_user.chat_id " +
            "where user.id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(User.class));
    }

    @Override
    public Optional<User> findById(Integer id) {
        Optional<User> user;
        try {
            user = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(User.class), id));
        } catch (EmptyResultDataAccessException e) {
            user = Optional.empty();
        }
        return user;
    }

    @Override
    public int create(User user) {
        return jdbcTemplate.update(CREATE, user.getName());
    }

    @Override
    public int update(Integer id, User user) {
        return jdbcTemplate.update(UPDATE, user.getName(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }

    @Override
    public Optional<User> findByName(String name) {
        Optional<User> user;
        try {
            user = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_NAME,
                    BeanPropertyRowMapper.newInstance(User.class), name));
        } catch (EmptyResultDataAccessException e) {
            user = Optional.empty();
        }
        return user;
    }

    @Override
    public List<Chat> findRelatedChats(Integer id) {
        List<Chat> chats;
        chats = jdbcTemplate.query(FIND_RELATED_CHATS,BeanPropertyRowMapper.newInstance(Chat.class), id);
        return chats;
    }
}
