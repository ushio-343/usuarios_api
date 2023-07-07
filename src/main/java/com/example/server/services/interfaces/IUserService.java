package com.example.server.services.interfaces;

import com.example.server.controllers.dtos.BaseResponse;
import com.example.server.controllers.dtos.CreateUserRequest;
import com.example.server.entitites.User;

public interface IUserService {
    BaseResponse create(CreateUserRequest request);
    User get(String email);
}
