package com.example.todolist.services;

import com.example.todolist.repositories.TodoMapper;
import com.example.todolist.models.TodoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoMapper todoMapper;

    public List<TodoItem> getAllTodoItem(){
        return todoMapper.findAll();
    }

    public TodoItem getTodoItemById(Long id){
        return todoMapper.findById(id);
    }

    public void addTodoItem(TodoItem todoItem){
        todoMapper.insert(todoItem);
    }

    public void updateTodoItem(TodoItem todoItem){
        todoMapper.update(todoItem);
    }

    public void deleteTodoItem(long id){
        todoMapper.deleteById(id);
    }
}
