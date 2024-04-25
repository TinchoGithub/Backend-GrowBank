package com.c1736.userservice.service;

public interface IAuthPasswordEncoderPort {
    String encodePassword(String decodedPassword);

    String decodePassword(String encodedPassword);
}
