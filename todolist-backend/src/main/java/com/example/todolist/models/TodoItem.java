package com.example.todolist.models;


import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TodoItem {
    private long id;
    private String task;
    private boolean completed;

}
