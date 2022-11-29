package com.mosorin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Chat {
    private Integer id;
    private String name;
    private Integer server_id;
}
