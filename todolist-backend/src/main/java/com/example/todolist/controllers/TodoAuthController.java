package com.example.todolist.controllers;

import com.example.todolist.DTOs.LoginRequests;
import com.example.todolist.models.TodoUser;
import com.example.todolist.services.TodoAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class TodoAuthController {

    private AuthenticationManager authenticationManager;


    public TodoAuthController(@RequestBody AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public String authenticateUser(@RequestBody LoginRequests loginRequests){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequests.getUsername(),
                            loginRequests.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

        }catch(AuthenticationException e){
            //na
            System.out.println("failure");
        }return "User logged in successfully";
    }
}
