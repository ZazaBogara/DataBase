package com.mosorin.lab5.domain;

import javax.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user", schema = "mosorinzakharlab4", catalog = "")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "user")
    private List<MessageEntity> messages;
    @ManyToMany
    @JoinTable(name = "chat_has_user", catalog = "", schema = "mosorinzakharlab4",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "chat_id", referencedColumnName = "id", nullable = false))
    private Set<ChatEntity> chats;

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
        UserEntity that = (UserEntity) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public List<MessageEntity> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageEntity> messages) {
        this.messages = messages;
    }

    public Set<ChatEntity> getChats() {
        return chats;
    }

    public void setChats(Set<ChatEntity> chats) {
        this.chats = chats;
    }
}
