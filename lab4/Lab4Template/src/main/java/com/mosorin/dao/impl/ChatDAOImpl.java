

package com.mosorin.dao.impl;

import com.mosorin.dao.ChatDAO;
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
public class ChatDAOImpl implements ChatDAO {
    private static final String FIND_ALL = "SELECT * FROM chat";
    private static final String CREATE = "INSERT chat(name, server_id) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE chat SET name = ?, server_id = ? WHERE id=?";
    private static final String DELETE = "DELETE FROM chat WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM chat WHERE id=?";
    private static final String FIND_BY_NAME = "SELECT * FROM chat WHERE name=?";

    private static final String FIND_RELATED_USERS = "SELECT user.id, user.name FROM chat " +
            "RIGHT JOIN chat_has_user ON chat.id = chat_has_user.chat_id " +
            "JOIN user ON user.id = chat_has_user.user_id " +
            "where chat.id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Chat> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Chat.class));
    }

    @Override
    public Optional<Chat> findById(Integer id) {
        Optional<Chat> chat;
        try {
            chat = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Chat.class), id));
        } catch (EmptyResultDataAccessException e) {
            chat = Optional.empty();
        }
        return chat;
    }

    @Override
    public int create(Chat chat) {
        return jdbcTemplate.update(CREATE, chat.getName(), chat.getServer_id());
    }

    @Override
    public int update(Integer id, Chat chat) {
        return jdbcTemplate.update(UPDATE, chat.getName(), chat.getServer_id(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }

    @Override
    public Optional<Chat> findByName(String name) {
        Optional<Chat> chat;
        try {
            chat = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_NAME,
                    BeanPropertyRowMapper.newInstance(Chat.class), name));
        } catch (EmptyResultDataAccessException e) {
            chat = Optional.empty();
        }
        return chat;
    }

    @Override
    public List<User> findRelatedUsers(Integer id) {
        List<User> users;
        users = jdbcTemplate.query(FIND_RELATED_USERS,BeanPropertyRowMapper.newInstance(User.class), id);
        return users;
    }
}
