package com.airbnb.service.impl;

import com.airbnb.entity.AppUser;
import com.airbnb.exception.UserExistException;
import com.airbnb.payload.LoginDto;
import com.airbnb.payload.UserDto;
import com.airbnb.repository.AppUserRepository;
import com.airbnb.service.AppUserService;
import com.airbnb.service.JWTService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {

    private AppUserRepository userRepository;
    private ModelMapper modelMapper;
    private JWTService jwtService;

    public AppUserServiceImpl(AppUserRepository userRepository, ModelMapper modelMapper, JWTService jwtService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
    }

    @Override
    public UserDto createUser(UserDto dto) {
      AppUser user=mapToEntity(dto);
        Optional<AppUser> existEmail=userRepository.findByEmail(user.getEmail());
        if(existEmail.isPresent()){
            throw new UserExistException("email already exists try with different email");
        }

        Optional<AppUser> existUserName=userRepository.findByUserName(user.getUserName());
        if(existUserName.isPresent()){
            throw new UserExistException("username already exists try new username");
        }

       String encryptedPass=BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(4));
        user.setPassword(encryptedPass);
        user.setRole("ROLE_USER");


        AppUser saved = userRepository.save(user);
        return mapToDto(saved);


    }

    @Override
    public String userlogin(LoginDto dto) {
        Optional<AppUser> byUserName = userRepository.findByUserName(dto.getUsername());
        if(byUserName.isPresent()){
          AppUser user=  byUserName.get();
             if(BCrypt.checkpw(dto.getPassword(),user.getPassword())){
                 String s = jwtService.generateToken(user);
                 return s;
             }
        }
        return null;
    }

    @Override
    public UserDto createPropertyOwner(UserDto dto) {
        AppUser user=mapToEntity(dto);
        Optional<AppUser> existEmail=userRepository.findByEmail(user.getEmail());
        if(existEmail.isPresent()){
            throw new UserExistException("email already exists try with different email");
        }

        Optional<AppUser> existUserName=userRepository.findByUserName(user.getUserName());
        if(existUserName.isPresent()){
            throw new UserExistException("username already exists try new username");
        }

        String encryptedPass=BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(4));
        user.setPassword(encryptedPass);
        user.setRole("ROLE_OWNER");


        AppUser saved = userRepository.save(user);
        return mapToDto(saved);
    }

    @Override
    public UserDto createPropertyManager(UserDto dto) {
        AppUser user=mapToEntity(dto);
        Optional<AppUser> existEmail=userRepository.findByEmail(user.getEmail());
        if(existEmail.isPresent()){
            throw new UserExistException("email already exists try with different email");
        }

        Optional<AppUser> existUserName=userRepository.findByUserName(user.getUserName());
        if(existUserName.isPresent()){
            throw new UserExistException("username already exists try new username");
        }

        String encryptedPass=BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(4));
        user.setPassword(encryptedPass);
        user.setRole("ROLE_MANAGER");


        AppUser saved = userRepository.save(user);
        return mapToDto(saved);
    }

    UserDto mapToDto(AppUser user){
        return modelMapper.map(user,UserDto.class);
    }

   AppUser mapToEntity(UserDto dto){
        return modelMapper.map(dto,AppUser.class);
    }
}
