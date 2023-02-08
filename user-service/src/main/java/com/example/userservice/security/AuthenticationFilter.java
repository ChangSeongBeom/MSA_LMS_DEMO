package com.example.userservice.security;


import com.example.userservice.dto.UserDto;
import com.example.userservice.service.UserService;
import com.example.userservice.dto.RequestLoginUserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@Slf4j
@AllArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private UserService userService;
    private Environment env;
    public AuthenticationFilter(AuthenticationManager authenticationManager,
                                UserService userService,Environment env){

        super.setAuthenticationManager(authenticationManager);
        this.userService=userService;
        this.env=env;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            RequestLoginUserDto creds = new ObjectMapper().readValue(request.getInputStream(), RequestLoginUserDto.class);
            System.out.println("====진입==="+creds);
            return getAuthenticationManager().authenticate(

                    new UsernamePasswordAuthenticationToken(
                            creds.getLoginId(),
                            creds.getPassword(),
                            new ArrayList<>()
                    )
            );
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void   successfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String userId=((User)authResult.getPrincipal()).getUsername();

        UserDto userDetails = userService.getUserDetailsById(userId);
        System.out.println("======userDetails====="+userDetails);
        String token= Jwts.builder()
                .setSubject(userDetails.getLoginId())
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(env.getProperty("token.expiration_time"))))
                .signWith(SignatureAlgorithm.HS512,env.getProperty("token.secret"))
                .compact();

        response.addHeader("token",token);
        response.addHeader("userId",userDetails.getLoginId());
    }
}