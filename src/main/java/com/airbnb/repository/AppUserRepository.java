package com.airbnb.repository;

import com.airbnb.entity.AppUser;
import com.airbnb.payload.UserDto;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);
    Optional<AppUser> findByUserName(String userName);
}