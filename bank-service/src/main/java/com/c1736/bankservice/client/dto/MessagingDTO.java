package com.c1736.bankservice.client.dto;

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
