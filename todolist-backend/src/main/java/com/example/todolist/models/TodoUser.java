package com.example.todolist.models;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TodoUser {
    private String user_id;
    private String uuid;
    private String email;
    private String password;
}
