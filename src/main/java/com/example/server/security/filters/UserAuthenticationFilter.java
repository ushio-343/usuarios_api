package com.example.server.security.filters;

import com.example.server.controllers.dtos.BaseResponse;
import com.example.server.security.user.AuthCredentials;
import com.example.server.security.user.UserDetailsImpl;
import com.example.server.utils.TokenUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collections;

public class UserAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                               HttpServletResponse response) throws AuthenticationException {

        AuthCredentials authCredentials = new AuthCredentials();
        try {
            authCredentials = new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);
        } catch (Exception ignored) {}

        UsernamePasswordAuthenticationToken uPAT = new UsernamePasswordAuthenticationToken(
                authCredentials.getEmail(),
                authCredentials.getPassword(),
                Collections.emptyList()
        );

        return getAuthenticationManager().authenticate(uPAT);

    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
        String token = TokenUtils.createToken(userDetails.getName(), userDetails.getUsername());

        response.addHeader("Authorization", "Bearer " + token);

        BaseResponse bResponse = BaseResponse.builder()
                .data("found")
                .success(true)
                .message("user logged token: ")
                .httpStatus(HttpStatus.ACCEPTED).build();

        response.getWriter().write(new ObjectMapper().writeValueAsString(bResponse));

        response.getWriter().flush();

        super.successfulAuthentication(request, response, chain, authResult);

    }

}
