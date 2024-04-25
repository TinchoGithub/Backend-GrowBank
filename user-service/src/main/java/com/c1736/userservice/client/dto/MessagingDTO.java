package com.c1736.userservice.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MessagingDTO {
    private String addressee;
    private String affair;
    private String message;
}
