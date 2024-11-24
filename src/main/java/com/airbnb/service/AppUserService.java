package com.airbnb.service;

import com.airbnb.payload.LoginDto;
import com.airbnb.payload.UserDto;

public interface AppUserService {
    UserDto createUser(UserDto user);

    String userlogin(LoginDto dto);

    UserDto createPropertyOwner(UserDto user);

    UserDto createPropertyManager(UserDto user);
}
