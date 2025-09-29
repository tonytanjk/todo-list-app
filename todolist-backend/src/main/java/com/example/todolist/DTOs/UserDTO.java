package com.example.todolist.DTOs;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.boot.convert.DataSizeUnit;

@Getter
@Setter
public class UserDTO {
    @NonNull
    private String username;
    private String email;
    private String uuid;
}
