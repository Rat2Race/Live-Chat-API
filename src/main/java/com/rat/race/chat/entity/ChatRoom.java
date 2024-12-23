package com.rat.race.chat.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ChatRoom implements Serializable {
    private static final long serialVersionUID = 1L;
    private String roomName;
    private String hostName;
}
