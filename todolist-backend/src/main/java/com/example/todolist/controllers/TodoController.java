package com.example.todolist.controllers;


import com.example.todolist.models.TodoItem;
import com.example.todolist.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {
    @Autowired
    private TodoService todoservice;

    @GetMapping("/list")
    public List<TodoItem> getAllTodos(){
        return todoservice.getAllTodoItem();
    }

    @GetMapping("/{id}")
    public TodoItem getTodoById(@PathVariable Long id){
        return todoservice.getTodoItemById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<TodoItem> createTodo(@RequestBody TodoItem todoItem){
        todoservice.addTodoItem(todoItem);
        return ResponseEntity.ok(todoItem);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TodoItem> updateTodo(@RequestBody TodoItem todoItem){
        todoservice.updateTodoItem(todoItem);
        return ResponseEntity.ok(todoItem);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTodo(@PathVariable Long id){
        todoservice.deleteTodoItem(id);
    }
}
