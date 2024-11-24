package com.airbnb.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String name;
    private String userName;
    private String email;
    private String password;
    private String role;
}
