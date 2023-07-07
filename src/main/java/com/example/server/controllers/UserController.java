package com.example.server.controllers;

import com.example.server.controllers.dtos.BaseResponse;
import com.example.server.controllers.dtos.CreateUserRequest;
import com.example.server.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {


    private final IUserService service;


    @Autowired
    public UserController(IUserService service){
        this.service = service;
    }


    @PostMapping("/reg")
    public ResponseEntity<BaseResponse> createUser(@RequestBody CreateUserRequest request){
        BaseResponse response = service.create(request);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }


}
