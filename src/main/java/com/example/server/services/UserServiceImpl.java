package com.example.server.services;

import com.example.server.controllers.dtos.BaseResponse;
import com.example.server.controllers.dtos.CreateUserRequest;
import com.example.server.controllers.dtos.GetUserResponse;
import com.example.server.entitites.User;
import com.example.server.repositories.IUserRepository;
import com.example.server.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {


    private final IUserRepository repository;


    @Autowired
    public UserServiceImpl(IUserRepository repository) {
        this.repository = repository;
    }


    @Override
    public BaseResponse create(CreateUserRequest request) {
        User user = repository.save(from(request));
        return BaseResponse.builder()
                .data(from(user))
                .message("the user was created")
                .success(true)
                .httpStatus(HttpStatus.CREATED).build();
    }


    @Override
    public User get(String email) {
        return repository.getUserByEmail(email).orElseThrow(RuntimeException::new);
    }


    private User from(CreateUserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setUserType(request.getType());
        user.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        return user;
    }


    private GetUserResponse from(User user) {
        return GetUserResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .id(user.getId())
                .userType(user.getUserType()).build();
    }


}
