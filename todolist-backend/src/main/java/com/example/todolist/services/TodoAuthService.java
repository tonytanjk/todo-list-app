package com.example.todolist.services;


import com.example.todolist.DTOs.UserDTO;
import com.example.todolist.repositories.TodoUserMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.webauthn.management.UserCredentialRepository;
import org.springframework.stereotype.Service;

@Service
public class TodoAuthService {

    private TodoUserMapper todoUserMapper;


    public TodoAuthService(TodoUserMapper todoUserMapper) {
        this.todoUserMapper = todoUserMapper;
    }

}
