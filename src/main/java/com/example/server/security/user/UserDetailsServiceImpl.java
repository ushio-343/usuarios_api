package com.example.server.security.user;

import com.example.server.entitites.User;
import com.example.server.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserService service;


    @Autowired
    public UserDetailsServiceImpl(IUserService service){
        this.service = service;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = service.get(username);
        return new UserDetailsImpl(user);
    }

}
