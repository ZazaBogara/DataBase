package com.mosorin.lab5.repository;


import com.mosorin.lab5.domain.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Integer> {
    MessageEntity findByText(String text);

}
