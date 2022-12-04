package com.mosorin.lab5.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;


@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "message", collectionRelation = "messages")
public class MessageDto extends RepresentationModel<MessageDto> {
    private final Integer id;
    private final String text;
    private final byte[] audio;
    private final byte[] photo;
    private final Integer chat_id;
    private final Integer user_id;
}
