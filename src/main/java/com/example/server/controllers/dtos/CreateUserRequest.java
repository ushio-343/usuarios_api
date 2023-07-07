package com.example.server.controllers.dtos;

import com.example.server.entitites.enums.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateUserRequest {

    private String name;
    private String email;
    private String password;
    private UserType type;

}
