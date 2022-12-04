package com.mosorin.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message {
    private Integer id;
    private Integer chat_id;
    private Integer user_id;
    private String text;
    private Date time;
}
