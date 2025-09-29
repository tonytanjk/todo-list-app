package com.example.todolist.repositories;


import com.example.todolist.DTOs.UserDTO;
import org.apache.catalina.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TodoUserMapper {
    UserDTO toDTO(User user);
    User toEntity(UserDTO userDTO);
}
