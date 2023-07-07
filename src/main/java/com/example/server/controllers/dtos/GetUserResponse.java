package com.example.server.controllers.dtos;

import com.example.server.entitites.enums.UserType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class GetUserResponse {

    private Long id;
    private String name;
    private String email;
    private UserType userType;

}
