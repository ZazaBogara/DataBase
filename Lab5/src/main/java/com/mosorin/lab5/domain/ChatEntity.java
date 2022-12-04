package com.mosorin.lab5.domain;

import javax.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "chat", schema = "mosorinzakharlab4", catalog = "")
public class ChatEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "server_id", referencedColumnName = "id", nullable = false)
    private ServerEntity server;
    @OneToMany(mappedBy = "chat")
    private List<MessageEntity> messages;
    @ManyToMany(mappedBy = "chats")
    private Set<UserEntity> users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatEntity that = (ChatEntity) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public ServerEntity getServer() {
        return server;
    }

    public void setServer(ServerEntity server) {
        this.server = server;
    }

    public List<MessageEntity> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageEntity> messages) {
        this.messages = messages;
    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }
}
