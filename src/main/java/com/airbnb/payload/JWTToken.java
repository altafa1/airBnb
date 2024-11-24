package com.airbnb.payload;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JWTToken {
    private String TokenType;
    private String token;
}
