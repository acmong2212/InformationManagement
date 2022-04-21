package com.informationmanagement.demo.dto.response;

import lombok.Data;

@Data
public class Message {
    private int status;
    private String message;

    public Message(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
